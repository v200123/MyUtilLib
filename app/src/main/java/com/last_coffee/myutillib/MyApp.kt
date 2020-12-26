package com.last_coffee.myutillib

import android.app.Application
import android.content.Context
import android.util.Log
import com.kongzue.dialogx.DialogX
import com.lc.liuchanglib.logger.SuperAndroidLogAdapter
import com.lc.liuchanglib.logger.SuperLogger
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
        SuperLogger().addLogAdapter(SuperAndroidLogAdapter())
        val initialize = MMKV.initialize(this)
        Log.i("LiuChang", "attachBaseContext: $initialize")

    }

    override fun onCreate() {
        super.onCreate()
        DialogX.init(this);
    }
}