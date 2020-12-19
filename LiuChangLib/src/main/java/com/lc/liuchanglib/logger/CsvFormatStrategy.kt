package com.lc.liuchanglib.logger

import android.os.Environment
import android.os.HandlerThread
import com.lc.liuchanglib.logger.SuperLogger.Companion.ASSERT
import com.lc.liuchanglib.logger.SuperLogger.Companion.DEBUG
import com.lc.liuchanglib.logger.SuperLogger.Companion.ERROR
import com.lc.liuchanglib.logger.SuperLogger.Companion.INFO
import com.lc.liuchanglib.logger.SuperLogger.Companion.VERBOSE
import com.lc.liuchanglib.logger.SuperLogger.Companion.WARN
import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy

import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：用于存储在磁盘的格式化格式
 * @Time: 十二月
 *
 **/
class CsvFormatStrategy(builder: Builder) : SuperFormatStrategy() {

    private val NEW_LINE = System.getProperty("line.separator")
    private val NEW_LINE_REPLACEMENT = " <br> "
    private val SEPARATOR = ","

    private var date: Date ?= null
    private var dateFormat: SimpleDateFormat ?= null
    private var logStrategy: SuperFormatStrategy? = null


    private var tag: String? = null
    init {
        date = builder.mDate
        dateFormat = builder.mDateFormat
        logStrategy = builder.mLogStrategy
        tag = builder.mTag
    }

    fun newBuilder(): Builder {
        return Builder()
    }

    override fun log(priority: Int, onceOnlyTag: String?, message: String?) {
        var message = message?:"信息为空"

        val tag = formatTag(onceOnlyTag?:"aaa")
        date!!.time = System.currentTimeMillis()
        val builder = StringBuilder()

        // machine-readable date/time
        builder.append(date!!.time.toString())

        // human-readable date/time
        builder.append(SEPARATOR)
        builder.append(dateFormat?.format(date))

        // level
        builder.append(SEPARATOR)
        builder.append(logLevel(priority))

        // tag
        builder.append(SEPARATOR)
        builder.append(tag)

        // message
        if (message.contains(NEW_LINE)) {
            // a new line would break the CSV format, so we replace it here
            message = message.replace(NEW_LINE.toRegex(), NEW_LINE_REPLACEMENT)
        }
        builder.append(SEPARATOR)
        builder.append(message)

        // new line
        builder.append(NEW_LINE)
        logStrategy?.log(priority, tag, builder.toString())
    }

    private fun logLevel(value: Int): String {

            when (value) {
                VERBOSE ->{
                    return "VERBOSE"
                }
                DEBUG ->{
                    return "DEBUG";
                }

                INFO ->{
                    return "INFO"
                }

                WARN ->{
                    return "WARN"

                }
                ERROR ->{
                    return "ERROR"
                }
              ASSERT ->{
                  return "ASSERT"
              }
                else ->
                return "UNKNOWN";
            }
    }


    private fun formatTag( tag: String): String? {
        return if (!tag.isEmpty() && this.tag!= tag) {
            this.tag + "-" + tag
        } else this.tag
    }

    class Builder() {
        var mDate: Date? = null
        var mDateFormat: SimpleDateFormat? = null
        var mLogStrategy: SuperFormatStrategy? = null
        var mTag = "PRETTY_LOGGER"
        fun date(date: Date?): Builder {
            mDate = date
            return this
        }

        fun dateFormat( dateFormat: SimpleDateFormat?): Builder {
            mDateFormat = dateFormat
            return this
        }

        fun logStrategy(logStrategy: SuperFormatStrategy?): Builder {
            mLogStrategy = logStrategy
            return this
        }

        fun tag( tag: String): Builder {
            mTag = tag
            return this
        }

        fun build(): CsvFormatStrategy {
            if (mDate == null) {
                mDate = Date()
            }
            if (mDateFormat == null) {
                mDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.UK)
            }
            if (mLogStrategy == null) {
                val diskPath: String = Environment.getDataDirectory().absolutePath
                val folder = diskPath + File.separatorChar.toString() + "logger"
                val ht = HandlerThread("AndroidFileLogger.$folder")
                ht.start()
//                val handler: Handler = WriteHandler(ht.looper, folder, MAX_BYTES)
//                logStrategy = DiskLogStrategy(handler)
            }
            return CsvFormatStrategy(this)
        }

        companion object {
            private const val MAX_BYTES = 500 * 1024 // 500K averages to a 4000 lines per file
        }
    }
}