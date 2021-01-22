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
            MMKV.defaultMMKV()!!.getString(UserToken,null)?.let {   request.newBuilder().addHeader("Authorization",it)
//                    .addHeader("User-Agent","Mozilla/5.0 (Linux; Android 7.1.1; SM-G973N Build/PPR1.190810.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 realme.android/2.4.2")
            }
        }

        return chain.proceed(request)
    }
}