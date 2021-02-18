package com.lc.liuchanglib.wechatUtils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lc.liuchanglib.R
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 *
 * @PackAge：com.lc.liuchanglib.pay
 * @创建人：Lc
 * @Desc：用于接收微信的支付回调的Activity
 * @Time: 二月
 *
 **/
class WXEntryActivity : AppCompatActivity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWechat.wxApi?.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        initWechat.wxApi?.handleIntent(intent, this)
    }

    override fun onReq(p0: BaseReq?) {

    }

    override fun onResp(baseResp: BaseResp) {
        var msg:String = ""
        when(baseResp.errCode){
            BaseResp.ErrCode.ERR_OK ->getString(R.string.wechat_success)
            BaseResp.ErrCode.ERR_USER_CANCEL ->getString(R.string.wechat_user_cancel)
            BaseResp.ErrCode.ERR_AUTH_DENIED ->getString(R.string.wechat_error)
            BaseResp.ErrCode.ERR_UNSUPPORT ->getString(R.string.wechat_unknown)

        }

        if(baseResp.type == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX)
        {
            WeChatOtherHelp.getInstance().doOtherCallBack(baseResp.errCode == 0,baseResp.errCode.toString(),msg)
            finish()
        }
        if(baseResp.type == ConstantsAPI.COMMAND_SENDAUTH)
        {
            val resp = baseResp as SendAuth.Resp
            WeChatOtherHelp.getInstance().doOtherCallBack(resp.errCode == 0,resp.code,msg)
            finish()

        }


    }


}