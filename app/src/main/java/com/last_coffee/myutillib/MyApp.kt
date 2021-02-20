package com.last_coffee.myutillib

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kongzue.dialogx.DialogX
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tencent.bugly.Bugly
import com.tencent.mmkv.MMKV


/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class MyApp : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        val initialize = MMKV.initialize(this)
        Bugly.init(this, "60089fa7a3", BuildConfig.DEBUG);
        Log.i("LiuChang", "attachBaseContext: $initialize")

    }

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        DialogX.init(this)
        Handler(mainLooper).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                }
            }
        }
        MyCrashHandler().init()
    }
}