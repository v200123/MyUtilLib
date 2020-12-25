package com.last_coffee.myutillib.myInterceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * @PackAge：com.last_coffee.myutillib.myInterceptor
 * @创建人：admin
 * @Desc：用于截获新的cookie
 * @Time: 十二月
 *
 **/
class ReceivedCookiesInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if(originalResponse.headers.size>0)
        {
            var s  = ""
            val values = originalResponse.headers.values("Set-Cookie")
            values[0].split(";").forEach{
                if(it.contains("XSRF_TOKEN"))
                {
                    s =  it.split("=")[1]
                    return@forEach
                }
            }

            Log.d("LiuChang", "当前请求有Header,需要获取Cookie：$s")
        }
        return originalResponse
    }
}