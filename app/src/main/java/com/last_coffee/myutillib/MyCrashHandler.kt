package com.last_coffee.myutillib

import android.os.Handler
import android.os.Looper
import android.util.Log

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 二月
 *
 **/
class MyCrashHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e("e", "Exception:" + e.message)



    }

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

}