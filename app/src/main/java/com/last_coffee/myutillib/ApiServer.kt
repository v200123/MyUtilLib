package com.last_coffee.myutillib

import com.last_coffee.myutillib.baseRequest.awardMissionRequest
import com.last_coffee.myutillib.bean.FinishMissionBean
import com.last_coffee.myutillib.bean.MissionList
import com.last_coffee.myutillib.bean.UserInfoBean
import com.last_coffee.myutillib.bean.UserTokenBean
import com.last_coffee.myutillib.bean.getTokenAuthBean
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
interface ApiServer {
    @POST("/api/check-in/status")
    suspend fun getCheckInStatue(
            @Header("User-Agent")agent:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
            @Header("Host")host :String= "www.realmebbs.com",
            @Header("Authorization")Authorization:String,
            @Header("Cookie")cookie:String = "XSRF-TOKEN=nSAxz34WvJHLbqQpzs8z4GSpnjgnTjEZ; GGSESSION=ZGMzYjVlZWUtYzZjZS00Y2VhLWI0NzAtNTI0MTAyZjBhZWZk; sessionId=iJ9aDeCtohqypOhNRujBdyR3CIhu_-2zs7TNY79ZV5-NPT11n20bxpgHWv5ci5G87w97sK76sZc; _ga=GA1.2.742200929.1606896379; _gid=GA1.2.1786067544.1608878855; _gat=1"):BaseRepose<CheckInStatueBean>

    @POST("api/check-in")
    suspend fun checkIn(
            @Header("User-Agent")agent:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
            @Header("Host")host :String= "www.realmebbs.com",
            @Header("Authorization")Authorization:String,
            @Header("Cookie")cookie:String
    ): BaseRepose<String>


    @GET ("api/account/profile")
    suspend fun getUserProfile(@Header("User-Agent")agent:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
                               @Header("Host")host :String= "www.realmebbs.com", @Header("Cookie")cookie:String ):BaseRepose<UserInfoBean>

    @POST("api/auth")
    suspend fun getUserToken(@Header("User-Agent")agent:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
                               @Header("Host")host :String= "www.realmebbs.com",
                             @Body token: getTokenAuthBean):BaseRepose<UserTokenBean>

    @GET("api/mission")
    suspend fun getMissList(@Header("User-Agent")agent:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
                             @Header("Host")host :String= "www.realmebbs.com",@Header("Cookie")cookie:String
                            ):MissionList

    @POST("api/mission/award")
    suspend fun awardMissionList(@Header("User-Agent")agent:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
                                 @Header("Host")host :String= "www.realmebbs.com",@Header("Cookie")cookie:String
    ,@Body request: awardMissionRequest): FinishMissionBean

}