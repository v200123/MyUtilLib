package com.lc.liuchanglib.logger

import android.util.Log
import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy
import com.lc.liuchanglib.logger.loggerInterface.SuperLogStrategy


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class SuperLogcatLogStrategy : SuperLogStrategy {
    private val DEFAULT_TAG = "NO_TAG"

    override fun log(priority: Int, tag: String?, message: String?) {
        var mTag = tag
        if (mTag == null) {
            mTag = DEFAULT_TAG
        }
        Log.println(priority, mTag, message?:"该次信息为空")
    }
}