package com.last_coffee.myutillib

import android.graphics.Bitmap
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.tencent.mmkv.MMKV


/**
* 
* @PackAge：com.last_coffee.myutillib
* @创建人：Lc
* @Desc：
* @Time: 十二月
* 
**/
class MyWebViewClient : WebViewClient() {


    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        return super.shouldInterceptRequest(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d(TAG, "onPageStarted: $url")

    }
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }
    override fun onPageFinished(view: WebView?, url: String?) {
        val cookieManager: CookieManager = CookieManager.getInstance()
        val CookieStr: String? = cookieManager.getCookie(url)
        if(CookieStr!=null)
        {
            if(CookieStr.contains("sessionId"))
            {
                CookieStr.split(";").forEach {
                    if(it.contains("sessionId"))
                    {
                       MMKV.defaultMMKV()!!.putString("session_id",it.split("=")[1])
                    }
                }
            }
        }
        if(!BuildConfig.DEBUG)
        {
            cookieManager.removeAllCookies {  }
        }
        Log.d(TAG, "onPageFinished: $CookieStr")
        super.onPageFinished(view, url)
    }
}