package com.lc.liuchanglib.wechatUtils

import android.content.Context
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 *
 * @PackAge：com.lc.liuchanglib.wechatUtils
 * @创建人：Lc
 * @Desc：初始化微信的注册流程
 * @Time: 二月
 *
 **/
class initWechat private constructor() {
    companion object {
        public var wxApi: IWXAPI? = null
        public fun getInstance() = initWechat()

        public fun initWechat(context: Context, APP_ID: String) {
            if (wxApi == null) {
                wxApi = WXAPIFactory.createWXAPI(context, APP_ID, true)
            }
            wxApi!!.registerApp(APP_ID)
        }
        fun checkIsInstall()=
                initWechat.wxApi?.isWXAppInstalled ?: throw IllegalArgumentException("请在调用该函数之前，调用initWechat方法")
    }

}