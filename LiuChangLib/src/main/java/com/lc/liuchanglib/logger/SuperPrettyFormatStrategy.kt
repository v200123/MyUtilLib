package com.lc.liuchanglib.logger
import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy
import com.lc.liuchanglib.logger.loggerInterface.SuperLogStrategy
import java.util.logging.Logger
import kotlin.math.min


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：格式化的一 个实现类
 * @Time: 十二月
 *
 **/
class SuperPrettyFormatStrategy private constructor(builder: Builder): SuperFormatStrategy {

    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    private val CHUNK_SIZE = 4000

    /**
     * The minimum stack trace index, starts at this class after two native calls.
     */
    private val MIN_STACK_OFFSET = 5

    /**
     * Drawing toolbox
     */
    private val TOP_LEFT_CORNER = '┌'
    private val BOTTOM_LEFT_CORNER = '└'
    private val MIDDLE_CORNER = '├'
    private val HORIZONTAL_LINE = '│'
    private val DOUBLE_DIVIDER = "────────────────────────────────────────────────────────"
    private val SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
    private val TOP_BORDER = TOP_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    private val BOTTOM_BORDER = BOTTOM_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    private val MIDDLE_BORDER = MIDDLE_CORNER.toString() + SINGLE_DIVIDER + SINGLE_DIVIDER

    private var methodCount = 0
    private var methodOffset = 0
    private var showThreadInfo = false
    private var logStrategy: SuperLogStrategy? = null
    
    private var tag: String? = null

    init {

        methodCount = builder.methodCount
        methodOffset = builder.methodOffset
        showThreadInfo = builder.showThreadInfo
        logStrategy = builder.logStrategy
        tag = builder.tag
    }

    fun newBuilder(): Builder {
        return Builder()
    }

    override fun log(priority: Int, tag: String?, message: String?) {

        val tag = formatTag(tag?:"aaa")
        logTopBorder(priority, tag)
        logHeaderContent(priority, tag, methodCount)

        //get bytes of message with system's default charset (which is UTF-8 for Android)
        val bytes = message?.toByteArray()?: byteArrayOf()
        val length = bytes.size
        if (length <= CHUNK_SIZE) {
            if (methodCount > 0) {
                logDivider(priority, tag)
            }

            logContent(priority, tag, message?:"没有任何信息")
            logBottomBorder(priority, tag)
            return
        }
        if (methodCount > 0) {
            logDivider(priority, tag)
        }
        var i = 0
        while (i < length) {
            val count = min(length - i, CHUNK_SIZE)
            //create a new String with system's default charset (which is UTF-8 for Android)
            logContent(priority, tag, String(bytes, i, count))
            i += CHUNK_SIZE
        }
        logBottomBorder(priority, tag)
    }

    private fun logTopBorder(logType: Int, tag: String?) {
        logChunk(logType, tag, TOP_BORDER)
    }

    private fun logHeaderContent(logType: Int, tag: String?, methodCount: Int) {
        var methodCount = methodCount
        val trace = Thread.currentThread().stackTrace
        if (showThreadInfo) {
            logChunk(logType, tag, HORIZONTAL_LINE.toString() + " Thread: " + Thread.currentThread().name)
            logDivider(logType, tag)
        }
        var level = ""
        val stackOffset = getStackOffset(trace) + methodOffset

        //corresponding method count with the current stack may exceeds the stack trace. Trims the count
        if (methodCount + stackOffset > trace.size) {
            methodCount = trace.size - stackOffset - 1
        }
        for (i in methodCount downTo 1) {
            val stackIndex = i + stackOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = StringBuilder()
            builder.append(HORIZONTAL_LINE)
                    .append(' ')
                    .append(level)
                    .append(getSimpleClassName(trace[stackIndex].className))
                    .append(".")
                    .append(trace[stackIndex].methodName)
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].fileName)
                    .append(":")
                    .append(trace[stackIndex].lineNumber)
                    .append(")")
            level += "   "
            logChunk(logType, tag, builder.toString())
        }
    }

    private fun logBottomBorder(logType: Int, tag: String?) {
        logChunk(logType, tag, BOTTOM_BORDER)
    }

    private fun logDivider(logType: Int, tag: String?) {
        logChunk(logType, tag, MIDDLE_BORDER)
    }

    private fun logContent(logType: Int, tag: String?, chunk: String) {

        val lines = chunk.split(System.getProperty("line.separator").toRegex()).toTypedArray()
        for (line in lines) {
            logChunk(logType, tag, "$HORIZONTAL_LINE $line")
        }
    }

    private fun logChunk(priority: Int, tag: String?, chunk: String) {

        logStrategy?.log(priority, tag, chunk)
    }

    private fun getSimpleClassName(name: String): String? {

        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace the stack trace
     * @return the stack offset
     */
    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        checkNotNull(trace)
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != LoggerPrinter::class.java.name && name != Logger::class.java.getName()) {
                return --i
            }
            i++
        }
        return -1
    }


    private fun formatTag(tag: String): String? {
        return if (this.tag == tag) {
            this.tag + "-" + tag
        } else this.tag
    }

    class Builder  constructor() {
        var methodCount = 2
        var methodOffset = 0
        var showThreadInfo = true


        var logStrategy: SuperLogStrategy? = null

        var tag = "PRETTY_LOGGER"
        fun methodCount(`val`: Int): Builder {
            methodCount = `val`
            return this
        }

        fun methodOffset(`val`: Int): Builder {
            methodOffset = `val`
            return this
        }

        fun showThreadInfo(`val`: Boolean): Builder {
            showThreadInfo = `val`
            return this
        }

        fun logStrategy(`val`: SuperLogStrategy?): Builder {
            logStrategy = `val`
            return this
        }

        fun tag(tag: String): Builder {
            this.tag = tag
            return this
        }

        fun build():   SuperPrettyFormatStrategy {
            if (logStrategy == null) {
                logStrategy = SuperLogcatLogStrategy()
            }
            return SuperPrettyFormatStrategy(this)
        }
    }



}