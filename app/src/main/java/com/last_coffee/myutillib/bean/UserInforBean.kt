package com.last_coffee.myutillib.bean
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 *
 * @PackAge：com.last_coffee.myutillib.bean
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
@JsonClass(generateAdapter = true)
data class UserInfoBean(
    @Json(name = "avatar")
    val mAvatar: String, // https://fs-uc-nearme-com-cn.oss-cn-hangzhou.aliyuncs.com/967/938/030/30839769.jpg
    @Json(name = "birthday")
    val mBirthday: String,
    @Json(name = "countryCode")
    val mCountryCode: Any?, // null
    @Json(name = "currentDevice")
    val mCurrentDevice: Any?, // null
    @Json(name = "editorGroupState")
    val mEditorGroupState: List<Any>,
    @Json(name = "followStatus")
    val mFollowStatus: Int, // 0
    @Json(name = "followersCount")
    val mFollowersCount: Int, // 2
    @Json(name = "followingsCount")
    val mFollowingsCount: Int, // 4
    @Json(name = "growthValue")
    val mGrowthValue: Int, // 67
    @Json(name = "id")
    val mId: String, // 30839769
    @Json(name = "intro")
    val mIntro: String, // 我是美雀
    @Json(name = "isBlocked")
    val mIsBlocked: Boolean, // false
    @Json(name = "medalCount")
    val mMedalCount: Int, // 5
    @Json(name = "phoneModel")
    val mPhoneModel: String, // PDRM00
    @Json(name = "postCount")
    val mPostCount: Int, // 5
    @Json(name = "sex")
    val mSex: String, // M
    @Json(name = "username")
    val mUsername: String // 用户1284430181
)