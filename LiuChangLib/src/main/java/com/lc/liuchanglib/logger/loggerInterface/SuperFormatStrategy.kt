package com.lc.liuchanglib.logger.loggerInterface


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
abstract class SuperFormatStrategy {
    protected val TOP_LEFT_CORNER = '┌'
    protected val BOTTOM_LEFT_CORNER = '└'
    protected val MIDDLE_CORNER = '├'
    protected val HORIZONTAL_LINE = '│'
    protected val DOUBLE_DIVIDER = "────────────────────────────────────────────────────────"
    protected val SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
    protected val TOP_BORDER = TOP_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    protected val BOTTOM_BORDER = BOTTOM_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    protected val MIDDLE_BORDER = MIDDLE_CORNER.toString() + SINGLE_DIVIDER + SINGLE_DIVIDER

    var mMethodCount = 0
    var mMthodOffset = 0
    var mShowThreadInfo = false
    var mLogStrategy: SuperLogStrategy? = null
    var mTag = "SuperLogger"
    abstract fun log(priority: Int, tag: String?, message: String?)
}