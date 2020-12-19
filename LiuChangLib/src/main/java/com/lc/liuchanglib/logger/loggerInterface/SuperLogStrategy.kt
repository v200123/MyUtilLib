package com.lc.liuchanglib.logger.loggerInterface


/**
 * log的策略
 */
interface SuperLogStrategy {
    fun log(priority: Int,tag: String?, message: String?)
}