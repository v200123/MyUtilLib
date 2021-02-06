package com.lc.liuchanglib.pay

import android.content.Context
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

class PayHelpUtil {
    private constructor()
    private lateinit var mPaySuccess:() -> Unit
    private lateinit var mPayError:(code: String, message: String) -> Unit
    companion object {
        public var wxApi: IWXAPI? = null



        public fun initWechat(mContext: Context, APP_ID: String) {
            if (wxApi == null) {
                wxApi = WXAPIFactory.createWXAPI(mContext, APP_ID, true)
            }
            wxApi!!.registerApp(APP_ID)
        }


    }

    public fun startPay(appId: String, partnerId: String, prepayId: String, packageValue: String,
                               nonceStr: String, timeStamp: String, sign: String, paySuccess: () -> Unit,
                               pauError: (code: String, message: String) -> Unit) {
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
        wxApi?.sendReq(payReq) ?: throw IllegalArgumentException("请在调用该函数之前，调用initWechat方法")
    }


}