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
data class MissionList(
    @Json(name = "code")
    val mCode: Int = 0, // 0
    @Json(name = "completeMission")
    val mCompleteMission: Any? = Any(), // null
    @Json(name = "data")
    val mData: List<Data> = listOf(),
    @Json(name = "message")
    val mMessage: String = "",
    @Json(name = "status")
    val mStatus: String = "" // success
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "behavior")
    val mBehavior: String = "", // check_in
    @Json(name = "behaviorCount")
    val mBehaviorCount: Int = 0, // 1
    @Json(name = "canDisplayInList")
    val mCanDisplayInList: Boolean = false, // true
    @Json(name = "continuesBehaviorCount")
    val mContinuesBehaviorCount: Int? = 0, // 1
    @Json(name = "dailyBehaviorCount")
    val mDailyBehaviorCount: Int? = 0, // 1
    @Json(name = "expiredAt")
    val mExpiredAt: Any? = Any(), // null
    @Json(name = "goldCoins")
    val mGoldCoins: Any? = Any(), // null
    @Json(name = "growthValue")
    val mGrowthValue: Int? = 0, // 1
    @Json(name = "id")
    val mId: Int = 0, // 6
    @Json(name = "isComplete")
    val mIsComplete: Boolean = false, // true
    @Json(name = "isDaily")
    val mIsDaily: Boolean = false, // true
    @Json(name = "isGetReward")
    val mIsGetReward: Boolean = false, // false
    @Json(name = "isParticipate")
    val mIsParticipate: Boolean = false, // true
    @Json(name = "medal")
    val mMedal: Medal? = Medal(),
    @Json(name = "medalId")
    val mMedalId: Int? = 0, // 1
    @Json(name = "name")
    val mName: String = "", // 签到
    @Json(name = "redirectTo")
    val mRedirectTo: String = "", // https://www.realmebbs.com/checkin
    @Json(name = "redirectType")
    val mRedirectType: String = "", // link
    @Json(name = "relateResource")
    val mRelateResource: Any? = Any(), // null
    @Json(name = "weight")
    val mWeight: Int = 0 // 200
)

@JsonClass(generateAdapter = true)
data class Medal(
    @Json(name = "condition")
    val mCondition: String = "", // 完成首次签到即可获得的小勋章
    @Json(name = "description")
    val mDescription: String = "", // 完成首次签到即可获得的小勋章
    @Json(name = "iconUrl")
    val mIconUrl: String = "", // https://r11.realme.net/CN/medal/1155684004725272576.png
    @Json(name = "id")
    val mId: Int = 0, // 1
    @Json(name = "isRead")
    val mIsRead: Boolean = false, // false
    @Json(name = "name")
    val mName: String = "", // 首签纪念
    @Json(name = "redirectTo")
    val mRedirectTo: Any? = Any(), // null
    @Json(name = "redirectType")
    val mRedirectType: Any? = Any(), // null
    @Json(name = "type")
    val mType: String = "" // check_in
)