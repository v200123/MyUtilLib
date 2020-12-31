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
data class FinishMissionBean(
    @Json(name = "code")
    val mCode: Int = 0, // 0
    @Json(name = "completeMission")
    val mCompleteMission: List<CompleteMission> = listOf(),
    @Json(name = "data")
    val mData: Any? = Any(), // null
    @Json(name = "message")
    val mMessage: String = "",
    @Json(name = "status")
    val mStatus: String = "" // success
)

@JsonClass(generateAdapter = true)
data class CompleteMission(
    @Json(name = "growthValue")
    val mGrowthValue: Int = 0, // 1
    @Json(name = "isComplete")
    val mIsComplete: Boolean = false, // true
    @Json(name = "isGetReward")
    val mIsGetReward: Boolean = false, // true
    @Json(name = "isParticipate")
    val mIsParticipate: Boolean = false, // true
    @Json(name = "medal")
    val mMedal: Any? = Any(), // null
    @Json(name = "missionId")
    val mMissionId: Any? = Any() // null
)