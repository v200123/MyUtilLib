package com.last_coffee.myutillib.baseRequest
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 *
 * @PackAge：com.last_coffee.myutillib.baseRequest
 * @创建人：Lc
 * @Desc：
 * @Time: 十二月
 *
 **/
@JsonClass(generateAdapter = true)
data class awardMissionRequest(
    @Json(name = "id")
    val mId: Int = 0// 6
)