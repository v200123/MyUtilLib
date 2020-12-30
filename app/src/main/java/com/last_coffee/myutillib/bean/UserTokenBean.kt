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
data class UserTokenBean(
    @Json(name = "token")
    val mToken: String = ""
)