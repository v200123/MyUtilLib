package com.last_coffee.liubaselib.httpUtils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 *
 * @PackAge：com.last_coffee.liubaselib.httpUtils
 * @创建人：Lc
 * @Desc：
 * @Time: 一月
 *
 **/

fun initRetrofit(init: (Retrofit.Builder).() -> Retrofit.Builder): Retrofit {

    return init(Retrofit.Builder()).build()
}

inline fun initOkHttp(tag:String = "com.lc.Log",init:
        (OkHttpClient.Builder.() -> OkHttpClient.Builder)): OkHttpClient
        {
            val okhttpClient = OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS)
                    .callTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)

            return init(okhttpClient).build()
        }



