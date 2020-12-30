package com.last_coffee.myutillib

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.last_coffee.liubaselib.BaseViewModel
import com.last_coffee.myutillib.bean.UserInfoBean
import com.last_coffee.myutillib.bean.UserTokenBean
import com.last_coffee.myutillib.bean.getTokenAuthBean
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.delay
import org.json.JSONObject

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
        val mToken = mmkv!!.decodeString("XSRF-TOKEN")
        val mGGSESSION = mmkv!!.decodeString("GGSESSION")
        var mUserToken = mmkv!!.decodeString("UserToken")

        mOtherMessage.value = "获取用户信息"
        launchTask {
            if(mToken == null)
            {
                //获取用户的XSRF_TOKEN
                getUserInfo(null,mSessionId!!,null)
                val userToken = getUserToken(mSessionId)
                userToken.mData?.let {
                    mUserToken = it.mToken
                    mmkv.putString("UserToken", mUserToken)
                }
            }
            val userInfo = getUserInfo(mToken!!, mSessionId!!,mGGSESSION!!)
            if(userInfo.mData!=null)
                mUserInfoData.value = userInfo.mData!!
            mOtherMessage.value = "检查签到状态"
            val checkInStatue =   getRetrofit().getCheckInStatue(Authorization ="$mUserToken",cookie = "XSRF-TOKEN=$mToken; GGSESSION=$mGGSESSION; sessionId=$mSessionId")
            if (checkInStatue.mCode == 0) {
                mCheckInStatueData.postValue(checkInStatue.mData!!)
                if (checkInStatue.mData.mCanCheckIn) {
                    mOtherMessage.value = "延迟3秒后开始签到"
                    delay(3000)
                    val checkIn: BaseRepose<String> =
                        getRetrofit().checkIn(Authorization ="$mUserToken",cookie = "XSRF-TOKEN=$mToken; GGSESSION=$mGGSESSION; sessionId=$mSessionId")
                    mCheckInData.value = if (checkIn.mMessage == null) "签到成功" else "今天已经签到"
                }else{
                    mCheckInData.value = "今日已经签到"
                }




            }



        }
    }



    private suspend fun getUserInfo(token:String?,session:String,mGGSESSION:String?)=
            if(token==null)
            {
                getRetrofit().getUserProfile(cookie =  "sessionId=${session}")
            }else
        getRetrofit().getUserProfile(cookie = "XSRF-TOKEN=${token}; GGSESSION=$mGGSESSION;sessionId=${session}; _ga=GA1.2.742200929.1606896379; _gid=GA1.2.1786067544.1608878855; _gat=1")

    private suspend fun getUserToken(sessionId:String):BaseRepose<UserTokenBean>{
        val tokenAuthBean = getTokenAuthBean(mSessionId = sessionId, "v2")
      return  getRetrofit().getUserToken(token = tokenAuthBean)

    }

}