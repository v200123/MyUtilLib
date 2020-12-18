package com.lc.liuchanglib.logger.loggerInterface


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：用于最终打印log
 * @Time: 十二月
 *
 **/
interface SuperLogStrategy {
    fun log(priority: Int, tag: String?, message: String)
}