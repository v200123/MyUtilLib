package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.loggerInterface.LogAdapter
import com.lc.liuchanglib.logger.loggerInterface.Printer


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class SuperLogger() {
    companion object{
       const val VERBOSE = 2
       const val DEBUG = 3
       const val INFO = 4
       const val WARN = 5
       const val ERROR = 6
       const val ASSERT = 7
    }

    private val printer: Printer = LoggerPrinter()

    fun addLogAdapter(adapter: LogAdapter) {
        printer.addAdapter(adapter)
    }

    fun clearLogAdapters() {
        printer.clearLogAdapters()
    }

    /**
     * Given tag will be used as tag only once for this method call regardless of the tag that's been
     * set during initialization. After this invocation, the general tag that's been set will
     * be used for the subsequent log calls
     */
    fun t(tag: String?): Printer? {
        return printer.t(tag)
    }

    /**
     * General log function that accepts all configurations as parameter
     */
    fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
        printer.log(priority, tag, message, throwable)
    }

    fun d(message: String, vararg args: Any?) {
        printer.d(message, *args)
    }

    fun d(`object`: Any?) {
        printer.d(`object`)
    }

    fun e(message: String, vararg args: Any?) {
        printer.e(message, *args)
    }

    fun e(throwable: Throwable?, message: String, vararg args: Any?) {
        printer.e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any?) {
        printer.i(message, *args)
    }

    fun v(message: String, vararg args: Any?) {
        printer.v(message, *args)
    }

    fun w(message: String, vararg args: Any?) {
        printer.w(message, *args)
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    fun wtf(message: String, vararg args: Any?) {
        printer.wtf(message, *args)
    }

    /**
     * Formats the given json content and print it
     */
    fun json(json: String?) {
        printer.json(json)
    }

    /**
     * Formats the given xml content and print it
     */
    fun xml(xml: String?) {
        printer.xml(xml)
    }
}