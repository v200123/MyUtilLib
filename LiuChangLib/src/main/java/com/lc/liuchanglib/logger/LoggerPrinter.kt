package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.SuperLogger.Companion.ASSERT
import com.lc.liuchanglib.logger.SuperLogger.Companion.DEBUG
import com.lc.liuchanglib.logger.SuperLogger.Companion.ERROR
import com.lc.liuchanglib.logger.SuperLogger.Companion.INFO
import com.lc.liuchanglib.logger.SuperLogger.Companion.VERBOSE
import com.lc.liuchanglib.logger.SuperLogger.Companion.WARN
import com.lc.liuchanglib.logger.loggerInterface.LogAdapter
import com.lc.liuchanglib.logger.loggerInterface.Printer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.PrintWriter
import java.io.StringReader
import java.io.StringWriter
import java.net.UnknownHostException
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class LoggerPrinter  : Printer {
    /**
     * It is used for json pretty print
     */
    private val JSON_INDENT = 2

    /**
     * Provides one-time used tag for the log message
     */
    private val localTag = ThreadLocal<String>()

    private val logAdapters: MutableList<LogAdapter> = ArrayList()

    override fun t(tag: String?): Printer? {
        if (tag != null) {
            localTag.set(tag)
        }
        return this
    }

    override fun d(message: String, vararg args: Any?) {
        log(DEBUG, null, message, args)
    }

    override fun d(`object`: Any?) {
        log(DEBUG, null, `object`.toString())
    }

    override fun e(message: String, vararg args: Any?) {
        e(null, message, *args)
    }

    override fun e(throwable: Throwable?, message: String, vararg args: Any?) {
        log(ERROR, throwable, message, args)
    }

    override fun w(message: String, vararg args: Any?) {
        log(WARN, null, message, args)
    }

    override fun i(message: String, vararg args: Any?) {
        log(INFO, null, message, args)
    }

    override fun v(message: String, vararg args: Any?) {
        log(VERBOSE, null, message, args)
    }

    override fun wtf(message: String, vararg args: Any?) {
        log(ASSERT, null, message, args)
    }

    override fun json(json: String?) {
            if(json == null)
                return

        if (json.isEmpty()) {
            d("Empty/Null json content")
            return
        }
        try {
            var mJson = json.trim { it <= ' ' }
            if (mJson.startsWith("{")) {
                val jsonObject = JSONObject(mJson)
                val message = jsonObject.toString(JSON_INDENT)
                d(message)
                return
            }
            if (mJson.startsWith("[")) {
                val jsonArray = JSONArray(mJson)
                val message = jsonArray.toString(JSON_INDENT)
                d(message)
                return
            }
            e("Invalid Json")
        } catch (e: JSONException) {
            e("Invalid Json")
        }
    }

    override fun xml(xml: String?) {
        if (xml.isNullOrBlank()) {
            d("Empty/Null xml content")
            return
        }
        try {
            val xmlInput: Source = StreamSource(StringReader(xml))
            val xmlOutput = StreamResult(StringWriter())
            val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
            transformer.transform(xmlInput, xmlOutput)
            d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"))
        } catch (e: TransformerException) {
            e("Invalid xml")
        }
    }

    @Synchronized
    override fun log(priority: Int,
                     tag: String?,
                     message: String?,
                     throwable: Throwable?) {
        var message = message?:""
        if (throwable != null && message != null) {
            message += " : " + getStackTraceString(throwable)
        }
        if (throwable != null && message == null) {
            message = getStackTraceString(throwable)
        }
        if (message.isEmpty()) {
            message = "Empty/NULL log message"
        }
        for (adapter in logAdapters) {
            if (adapter.isLoggable(priority, tag)) {
                adapter.log(priority, tag, message)
            }
        }
    }

    override fun clearLogAdapters() {
        logAdapters.clear()
    }

    override fun addAdapter(adapter: LogAdapter) {
        logAdapters.add(checkNotNull(adapter))
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    @Synchronized
    private fun log(priority: Int,
                    throwable: Throwable?,
                    msg: String,
                    vararg args: Any) {
        checkNotNull(msg)
        val tag = getTag()
        val message = createMessage(msg, *args)
        log(priority, tag, message, throwable)
    }

    /**
     * @return the appropriate tag based on local or global
     */
    
    private fun getTag(): String? {
        val tag = localTag.get()
        if (tag != null) {
            localTag.remove()
            return tag
        }
        return null
    }

    private fun createMessage(message: String, vararg args: Any): String {
        return if (args.isEmpty()) message else String.format(message, *args)
    }

    fun getStackTraceString(tr: Throwable?):String{
        if (tr == null) {
            return ""
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        var t = tr
        while (t != null) {
            if (t is UnknownHostException) {
                return ""
            }
            t = t.cause
        }
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }

}