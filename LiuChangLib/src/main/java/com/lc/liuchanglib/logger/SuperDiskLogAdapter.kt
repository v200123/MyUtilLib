package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.loggerInterface.LogAdapter

class SuperDiskLogAdapter : SuperBaseLogAdapter(CsvFormatStrategy.Builder().build()),LogAdapter {



    override fun isLoggable(priority: Int, tag: String?): Boolean = true

    override fun log(priority: Int, tag: String?, message: String?) {
        mFormatStrategy.log(priority,tag,message)
    }
}