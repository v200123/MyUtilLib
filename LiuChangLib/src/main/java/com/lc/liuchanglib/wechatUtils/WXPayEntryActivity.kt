package com.lc.liuchanglib.wechatUtils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 *
 * @PackAge：com.lc.liuchanglib.pay
 * @创建人：Lc
 * @Desc：用于接收微信的支付回调的Activity
 * @Time: 二月
 *
 **/
class WXPayEntryActivity : AppCompatActivity(), IWXAPIEventHandler {

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
        if(baseResp.type == ConstantsAPI.COMMAND_PAY_BY_WX)
        {
            PayHelpUtil.getInstance().payCallback(baseResp.errCode == 0,baseResp.errCode.toString(),baseResp.errStr)
        }
        finish()
    }


}