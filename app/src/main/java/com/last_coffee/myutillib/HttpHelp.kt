package com.last_coffee.myutillib

import com.last_coffee.myutillib.myInterceptor.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
    return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://www.realmebbs.com")
            .client(getOkHttpClient())
            .build().create(ApiServer::class.java)
}

fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
                .apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .addInterceptor(ReceivedCookiesInterceptor()).build()
