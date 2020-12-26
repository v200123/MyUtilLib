package com.last_coffee.myutillib

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.last_coffee.liubaselib.BaseViewModel
import com.last_coffee.myutillib.bean.UserInfoBean
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.delay

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
class MainViewModel : BaseViewModel() {
    val mmkv = MMKV.defaultMMKV()
    val mCheckInStatueData = MutableLiveData<CheckInStatueBean>()
    val mUserInfoData = MutableLiveData<UserInfoBean>()
    val mCheckInData = MutableLiveData<String>()
    val mOtherMessage = MutableLiveData<String>()
    fun getCheckStatue() {
        val mSessionId = mmkv!!.decodeString("session_id")
        val mToken = mmkv!!.decodeString("Token")
        mOtherMessage.value = "获取用户信息"
        launchTask {

            val userInfo = getUserInfo(mToken!!, mSessionId!!)
            if(userInfo.mData!=null)
                mUserInfoData.value = userInfo.mData!!
            mOtherMessage.value = "检查签到状态"

            val checkInStatue =   getRetrofit().getCheckInStatue(cookie = "XSRF-TOKEN=$mToken; GGSESSION=ZGMzYjVlZWUtYzZjZS00Y2VhLWI0NzAtNTI0MTAyZjBhZWZk; sessionId=$mSessionId; _ga=GA1.2.742200929.1606896379; _gid=GA1.2.1786067544.1608878855; _gat=1")
            if (checkInStatue.mCode == 0) {
                mCheckInStatueData.postValue(checkInStatue.mData!!)
                if (!checkInStatue.mData.mCanCheckIn) {
                    delay(3000)
                    Log.d(TAG, "发起下一次请求：${System.currentTimeMillis()}")
                    val checkIn: BaseRepose<String> =
                        getRetrofit().checkIn(cookie = "XSRF-TOKEN=${mmkv.decodeString("Token")}; GGSESSION=ZGMzYjVlZWUtYzZjZS00Y2VhLWI0NzAtNTI0MTAyZjBhZWZk; sessionId=$mSessionId; _ga=GA1.2.742200929.1606896379; _gid=GA1.2.1786067544.1608878855; _gat=1")
                    mCheckInData.value = if (checkIn.mMessage == null) "签到成功" else "今天已经签到"
                }




            }



        }
    }



    private suspend fun getUserInfo(token:String,session:String)=
        getRetrofit().getUserProfile(cookie = "XSRF-TOKEN=${token}/*; GGSESSION=ZGMzYjVlZWUtYzZjZS00Y2VhLWI0NzAtNTI0MTAyZjBhZWZk;*/ sessionId=${session}; _ga=GA1.2.742200929.1606896379; _gid=GA1.2.1786067544.1608878855; _gat=1")



}