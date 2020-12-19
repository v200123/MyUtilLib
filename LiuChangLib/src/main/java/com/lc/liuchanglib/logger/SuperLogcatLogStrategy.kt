package com.lc.liuchanglib.logger

import android.util.Log
import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy


/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class SuperLogcatLogStrategy : SuperFormatStrategy() {
    private val DEFAULT_TAG = "NO_TAG"

    override fun log(priority: Int, tag: String?, message: String?) {
        var tag = tag
        if (tag == null) {
            tag = DEFAULT_TAG
        }
        Log.println(priority, tag, message?:"该次信息为空")
    }
}