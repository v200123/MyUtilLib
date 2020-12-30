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
    val mAvatar: String? = "",
    @Json(name = "birthday")
    val mBirthday: Any? = Any(),
    @Json(name = "countryCode")
    val mCountryCode: Any? = Any(),
    @Json(name = "currentDevice")
    val mCurrentDevice: Any? = Any(),
    @Json(name = "editorGroupState")
    val mEditorGroupState: List<Any>? = listOf(),
    @Json(name = "followStatus")
    val mFollowStatus: Int? = 0,
    @Json(name = "followersCount")
    val mFollowersCount: Int? = 0,
    @Json(name = "followingsCount")
    val mFollowingsCount: Int? = 0,
    @Json(name = "growthValue")
    val mGrowthValue: Int? = 0,
    @Json(name = "id")
    val mId: String? = "",
    @Json(name = "intro")
    val mIntro: String? = "",
    @Json(name = "isBlocked")
    val mIsBlocked: Boolean? = false,
    @Json(name = "medalCount")
    val mMedalCount: Int? = 0,
    @Json(name = "phoneModel")
    val mPhoneModel: Any? = Any(),
    @Json(name = "postCount")
    val mPostCount: Int? = 0,
    @Json(name = "sex")
    val mSex: Any? = Any(),
    @Json(name = "username")
    val mUsername: String? = ""
)