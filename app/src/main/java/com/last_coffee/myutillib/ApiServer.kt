package com.last_coffee.myutillib

import com.last_coffee.myutillib.baseRequest.awardMissionRequest
import com.last_coffee.myutillib.bean.*
import com.tencent.mmkv.MMKV
import org.json.JSONObject
import retrofit2.http.*

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


    @GET("api/index/recommend")
    suspend fun getTopicList(@Query("size")size:String = "20", @Query("page")page:String = "1", @Query("product")product:Boolean = false):getTopicListBean

    @POST("api/thread/{ArticleId}/like")
    suspend fun likeSomeArticle(
            @Header("Authorization")auth:String? = MMKV.defaultMMKV()!!.getString(UserToken,""),
            @Header("User-Agent")agent:String = "Mozilla/5.0 (Linux; Android 5.1.1; SM-G973N Build/PPR1.190810.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 realme.android/2.4.2",
            @Path("ArticleId")ArticleId:String):BaseRepose<String?>
}