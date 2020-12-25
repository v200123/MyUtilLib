package com.last_coffee.myutillib
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
@JsonClass(generateAdapter = true)
data class CheckInStatueBean(
    @Json(name = "canCheckIn")
    val mCanCheckIn: Boolean, // false
    @Json(name = "continuousCheckInDays")
    val mContinuousCheckInDays: Int // 5
)