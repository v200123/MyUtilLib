package com.last_coffee.myutillib

import android.util.Log
import com.last_coffee.liubaselib.httpUtils.initOkHttp
import com.last_coffee.liubaselib.httpUtils.initRetrofit
import com.last_coffee.myutillib.myInterceptor.HeaderInterceptor
import com.last_coffee.myutillib.myInterceptor.ReceivedCookiesInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/

fun getRetrofit():ApiServer{
    return initRetrofit { baseUrl("https://www.realmebbs.com")
                addConverterFactory(MoshiConverterFactory.create())
                        .client(initOkHttp {
                            addInterceptor(ReceivedCookiesInterceptor())
                            addInterceptor(HeaderInterceptor())
                            addInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                        })}.create(ApiServer::class.java)
}


