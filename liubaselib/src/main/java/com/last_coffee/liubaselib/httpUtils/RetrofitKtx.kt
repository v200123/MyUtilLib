package com.last_coffee.liubaselib.httpUtils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

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

inline fun initOkHttp(tag: String = "com.lc.Log", needMoreSafe: Boolean = false, crossinline hostNameVerifier: (hostname: String, session: SSLSession) -> Boolean = { _,_ -> true }, sslFactory: SSLSocketFactory? = null, trustManager: X509TrustManager? = null, init:
(OkHttpClient.Builder.() -> OkHttpClient.Builder)): OkHttpClient {
    val okhttpClient = OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS)
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
    if (needMoreSafe) {
        if (sslFactory == null || trustManager == null)
            throw  IllegalArgumentException("既然开启了安全模式，就请重写sslFactory和trustManager吧")
        okhttpClient
                .hostnameVerifier { hostname, session -> hostNameVerifier(hostname, session) }
                .sslSocketFactory(sslFactory, trustManager)
    }
    return init(okhttpClient).build()
}







