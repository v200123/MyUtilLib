package com.lc.liuchanglib.wechatUtils

import android.content.Context
import android.widget.Toast
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage

/**
 *
 * @PackAge：com.lc.liuchanglib.wechatUtils
 * @创建人：Lc
 * @Desc：微信登录的回调类
 * @Time: 二月
 *
 **/
typealias Success = (message: String) -> Unit
typealias Error = (errorCode: String, message: String) -> Unit

class WeChatOtherHelp private constructor() {
    //微信成功的回调
    private lateinit var mDoSuccess: Success

    //微信失败的回调
    private lateinit var mDoError: Error

    companion object {

        private var sWeChatOtherHelp: WeChatOtherHelp? = null
        public fun getInstance() = sWeChatOtherHelp ?: WeChatOtherHelp()
    }

    /**
     * 发送后的回调信息
     */
    public fun doOtherCallBack(isSuccess: Boolean, errorCode: String, message: String) {
        if (isSuccess) mDoSuccess(message) else mDoError(errorCode, message)
    }

    /**
     * @param block 等待发送到微信的信息
     * @param mediaObject 等待发送到微信的详细信息，如：分享的内容等
     * @param target 想要发送到微信的目标地址：朋友圈，收藏，对话框等
     * @param success 发送成功后想要做什么
     * @param error 发送失败后需要做什么
     * -[发送到朋友圈][SendMessageToWX.Req.WXSceneTimeline]
     * -[发送到对话][SendMessageToWX.Req.WXSceneSession]
     * -[发送到收藏][SendMessageToWX.Req.WXSceneFavorite]
     *
     */
    public fun startDoShare( block: WXMediaMessage.() -> Unit, mediaObject: WXMediaMessage.IMediaObject?, target: Int, unInstallWechat: () -> Unit = {  }, success: Success, error: Error) {
        if (initWechat.checkIsInstall()) {
            mDoSuccess = success
            mDoError = error
            val wxMediaMessage = WXMediaMessage()
            block(wxMediaMessage)
            wxMediaMessage.mediaObject = mediaObject
            val req = SendMessageToWX.Req()
            req.message = wxMediaMessage
            req.scene = target
            initWechat.wxApi?.sendReq(req)
        } else {
            unInstallWechat()
        }

    }
    public fun startDoAuth(unInstallWechat: () -> Unit = {  }, success: Success, error: Error){
        if (initWechat.checkIsInstall()) {
            mDoSuccess = success
            mDoError = error
        }else {
            unInstallWechat()
        }
    }


}