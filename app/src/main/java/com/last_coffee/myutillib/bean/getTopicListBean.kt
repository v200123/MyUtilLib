package com.last_coffee.myutillib.bean
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
* 
* @PackAge：com.last_coffee.myutillib.bean
* @创建人：Lc
* @Desc：获取首页所有文章
* @Time: 一月
* 
**/@JsonClass(generateAdapter = true)
data class getTopicListBean(
    @Json(name = "code")
    val mCode: Int, // 0
    @Json(name = "completeMission")
    val mCompleteMission: Any?, // null
    @Json(name = "data")
    val mData: List<Data>,
    @Json(name = "message")
    val mMessage: String,
    @Json(name = "page")
    val mPage: Page,
    @Json(name = "status")
    val mStatus: String // success
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "author")
        val mAuthor: Author,
        @Json(name = "bottomAt")
        val mBottomAt: Any?, // null
        @Json(name = "commentCount")
        val mCommentCount: String, // 80
        @Json(name = "compCovers")
        val mCompCovers: Any?, // null
        @Json(name = "contentRaw")
        val mContentRaw: Any?, // null
        @Json(name = "covers")
        val mCovers: List<String>,
        @Json(name = "excerpt")
        val mExcerpt: String, // Hey,guys!欢迎来到本周五的有料有聊~
        @Json(name = "forum")
        val mForum: Forum,
        @Json(name = "hasVacHidden")
        val mHasVacHidden: Any?, // null
        @Json(name = "id")
        val mId: Long, // 1349915974786101200
        @Json(name = "idString")
        val mIdString: String, // 1349915974786101248
        @Json(name = "isAuthorReadOnly")
        val mIsAuthorReadOnly: Boolean, // false
        @Json(name = "isFavorite")
        val mIsFavorite: Boolean, // false
        @Json(name = "isLike")
        val mIsLike: Boolean, // false
        @Json(name = "isTop")
        val mIsTop: Boolean, // false
        @Json(name = "likeCount")
        val mLikeCount: String, // 74
        @Json(name = "phoneModel")
        val mPhoneModel: String,
        @Json(name = "productLink")
        val mProductLink: Any?, // null
        @Json(name = "productRecommendId")
        val mProductRecommendId: Any?, // null
        @Json(name = "productSkuid")
        val mProductSkuid: Any?, // null
        @Json(name = "productSpuid")
        val mProductSpuid: Any?, // null
        @Json(name = "publishedAt")
        val mPublishedAt: Int, // 1610680023
        @Json(name = "source")
        val mSource: Any?, // null
        @Json(name = "status")
        val mStatus: Int, // 0
        @Json(name = "statusName")
        val mStatusName: String, // 待处理
        @Json(name = "tags")
        val mTags: List<Any>,
        @Json(name = "title")
        val mTitle: String, // 有料有聊 | 第33话：今年的你回家过年吗？
        @Json(name = "videoThumbnailUrl")
        val mVideoThumbnailUrl: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Author(
            @Json(name = "avatar")
            val mAvatar: String, // https://r11.realme.net/CN/avatar/1212578603418529792.png
            @Json(name = "currentDevice")
            val mCurrentDevice: Any?, // null
            @Json(name = "editorGroupState")
            val mEditorGroupState: List<EditorGroupState>,
            @Json(name = "followStatus")
            val mFollowStatus: Int, // 0
            @Json(name = "medals")
            val mMedals: List<Medal>,
            @Json(name = "phoneModel")
            val mPhoneModel: Any?, // null
            @Json(name = "userId")
            val mUserId: String, // 487089048
            @Json(name = "username")
            val mUsername: String // realme活动君
        ) {
            @JsonClass(generateAdapter = true)
            data class EditorGroupState(
                @Json(name = "name")
                val mName: String, // 版主
                @Json(name = "status")
                val mStatus: Boolean, // true
                @Json(name = "type")
                val mType: String // a
            )

            @JsonClass(generateAdapter = true)
            data class Medal(
                @Json(name = "condition")
                val mCondition: String, // 社区VIP 2级真粉
                @Json(name = "description")
                val mDescription: String, // 社区VIP 2级真粉
                @Json(name = "iconUrl")
                val mIconUrl: String, // https://r11.realme.net/CN/medal/1167389984341110784.png
                @Json(name = "id")
                val mId: Int, // 11
                @Json(name = "isRead")
                val mIsRead: Boolean, // true
                @Json(name = "name")
                val mName: String, // 元气新手
                @Json(name = "redirectTo")
                val mRedirectTo: Any?, // null
                @Json(name = "redirectType")
                val mRedirectType: Any?, // null
                @Json(name = "type")
                val mType: String // normal
            )
        }

        @JsonClass(generateAdapter = true)
        data class Forum(
            @Json(name = "appCoverImageUrl")
            val mAppCoverImageUrl: Any?, // null
            @Json(name = "id")
            val mId: Long, // 1154980723988377600
            @Json(name = "idString")
            val mIdString: String, // 1154980723988377600
            @Json(name = "isBugReport")
            val mIsBugReport: Boolean, // false
            @Json(name = "isEditLimited")
            val mIsEditLimited: Boolean, // false
            @Json(name = "name")
            val mName: String, // 真粉杂谈
            @Json(name = "webCoverImageUrl")
            val mWebCoverImageUrl: Any? // null
        )
    }

    @JsonClass(generateAdapter = true)
    data class Page(
        @Json(name = "currentPage")
        val mCurrentPage: Int, // 1
        @Json(name = "isFirst")
        val mIsFirst: Boolean, // true
        @Json(name = "isLast")
        val mIsLast: Boolean, // false
        @Json(name = "size")
        val mSize: Int, // 20
        @Json(name = "totalElements")
        val mTotalElements: Int, // 2342
        @Json(name = "totalPages")
        val mTotalPages: Int // 118
    )
}