package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.loggerInterface.LogAdapter

class SuperDiskLogAdapter : SuperBaseLogAdapter(),LogAdapter {



    override fun isLoggable(priority: Int, tag: String?): Boolean = true

    override fun log(priority: Int, tag: String?, message: String?) {
        TODO("Not yet implemented")
    }
}