package com.lc.liuchanglib.wechatUtils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 *
 * @PackAge：com.lc.liuchanglib.pay
 * @创建人：Lc
 * @Desc：
 * @Time: 二月
 *
 **/

class PayHelpUtil private constructor() {
    private lateinit var mPaySuccess:() -> Unit

    private lateinit var mPayError:(code: String, message: String) -> Unit
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext : Context? = null
        public fun getInstance() = PayHelpUtil()
    }

    public fun payCallback(isSuccess:Boolean,code: String, message: String){
        if(isSuccess)
            mPaySuccess()
        else
            mPayError(code,message)
    }

    public fun startPay(appId: String, partnerId: String, prepayId: String, packageValue: String,
                               nonceStr: String, timeStamp: String, sign: String, paySuccess: () -> Unit,
                               pauError: (code: String, message: String) -> Unit) {
        if(checkIsInstall())
        {
            mPaySuccess = paySuccess
            mPayError = pauError
            val payReq = PayReq()
            payReq.appId = appId
            payReq.partnerId = partnerId
            payReq.prepayId = prepayId
            payReq.packageValue = packageValue
            payReq.sign = sign
            payReq.timeStamp = timeStamp
            payReq.nonceStr = nonceStr
            initWechat.wxApi?.sendReq(payReq) ?: throw IllegalArgumentException("请在调用该函数之前，调用initWechat方法")}
        else{
            Toast.makeText(mContext,"没有安装微信",Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkIsInstall()=
        initWechat.wxApi?.isWXAppInstalled ?: throw IllegalArgumentException("请在调用该函数之前，调用initWechat方法")


}