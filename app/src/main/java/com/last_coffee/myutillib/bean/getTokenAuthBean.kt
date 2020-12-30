package com.last_coffee.myutillib.bean
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 *
 * @PackAge：com.last_coffee.myutillib.bean
 * @创建人：Lc
 * @Desc：
 * @Time: 十二月
 *
 **/
@JsonClass(generateAdapter = true)
data class getTokenAuthBean(
    @Json(name = "sessionId")
    val mSessionId: String = "",
    @Json(name = "version")
    val mVersion: String = ""
)