package com.last_coffee.myutillib.myInterceptor

import com.last_coffee.myutillib.UserToken
import com.tencent.mmkv.MMKV
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * @PackAge：com.last_coffee.myutillib.myInterceptor
 * @创建人：Lc
 * @Desc：
 * @Time: 十二月
 *
 **/
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if(request.header("Authorization") == null)
        {
            MMKV.defaultMMKV()!!.getString(UserToken,null)?.let {   request.newBuilder().addHeader("Authorization",it)}

        }
        return chain.proceed(request)
    }
}