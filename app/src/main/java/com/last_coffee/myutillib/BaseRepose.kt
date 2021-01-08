package com.last_coffee.myutillib
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json
import com.tencent.bugly.proguard.T


/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
@JsonClass(generateAdapter = true)
data class BaseRepose<V>(
        @Json(name = "code")
    val mCode: Int, // 0
        @Json(name = "completeMission")
    val mCompleteMission: Any?, // null
        @Json(name = "data")
    val mData: V?,
        @Json(name = "message")
    val mMessage: String?,
        @Json(name = "status")
    val mStatus: String // success
)

