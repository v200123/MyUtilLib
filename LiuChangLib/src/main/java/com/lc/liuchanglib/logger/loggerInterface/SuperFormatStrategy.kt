package com.lc.liuchanglib.logger.loggerInterface


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
interface SuperFormatStrategy {
    fun log(priority: Int, tag: String?, message: String?)
}