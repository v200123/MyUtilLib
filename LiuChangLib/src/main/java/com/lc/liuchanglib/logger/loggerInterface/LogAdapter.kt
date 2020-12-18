package com.lc.liuchanglib.logger.loggerInterface


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
interface LogAdapter {
    fun  isLoggable(priority: Int, tag: String?) : Boolean
    fun log(priority: Int, tag: String?, message: String?)
}