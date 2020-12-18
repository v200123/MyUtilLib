package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.loggerInterface.LogAdapter
import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy

/**
 *
 * @PackAge：com.lc.liuchanglib.logger
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class SuperAndroidLogAdapter(formatStrategy:SuperFormatStrategy =  SuperPrettyFormatStrategy.Builder().build()) : LogAdapter {
    private var mFormatStrategy: SuperFormatStrategy? = null
    init {
        mFormatStrategy = checkNotNull(formatStrategy);
    }
    override fun isLoggable(priority: Int, tag: String?): Boolean {
       return true
    }

    override fun log(priority: Int, tag: String?, message: String?) {
        mFormatStrategy!!.log(priority,tag,message)
    }
}