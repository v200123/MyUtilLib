package com.last_coffee.myutillib

import android.app.Application
import android.content.Context
import com.lc.liuchanglib.logger.SuperAndroidLogAdapter
import com.lc.liuchanglib.logger.SuperLogger



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
    }
}