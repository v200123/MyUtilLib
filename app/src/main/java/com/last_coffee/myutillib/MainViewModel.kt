package com.last_coffee.myutillib

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.last_coffee.liubaselib.BaseViewModel
import com.last_coffee.myutillib.baseRequest.awardMissionRequest
import com.last_coffee.myutillib.bean.UserInfoBean
import com.last_coffee.myutillib.bean.UserTokenBean
import com.last_coffee.myutillib.bean.getTokenAuthBean
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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
        val mSessionId = mmkv!!.decodeString(SESSIONID)
        val mToken = mmkv!!.decodeString(XSRF_TOKEN)
        val mGGSESSION = mmkv!!.decodeString(GGSESSION)
        var mUserToken = mmkv!!.decodeString(UserToken)

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

                    val checkIn: BaseRepose<String> =
                        getRetrofit().checkIn(Authorization ="$mUserToken",cookie = "XSRF-TOKEN=$mToken; GGSESSION=$mGGSESSION; sessionId=$mSessionId")
                    mCheckInData.value = if (checkIn.mMessage == null) "签到成功" else "今天已经签到"
                }else{
                    mCheckInData.value = "今日已经签到"
                }
                delay(1000)
                mOtherMessage.value = "检测任务完成情况"
                val mNoGetRewardList = mutableListOf<Int>()
                val missionList = getMissionList(mToken!!, mSessionId!!, mGGSESSION!!)
                if(missionList.mStatus == "success")
                {
                    missionList.mData?.forEach {
                        if(it.mIsComplete)
                        {
                            if(!it.mIsGetReward)
                            mNoGetRewardList.add(it.mId)
                            mCheckInData.value = "${it.mName}\t\t已完成，是否领取奖励：${if(it.mIsGetReward)"领取" else "未领取"}"

                        }
                        else{
                            mCheckInData.value = "${it.mName}\t\t未完成"
                        }
                        if(mNoGetRewardList.size>0) {
                            mOtherMessage.value = "开始完成未领取任务"
                            finishMissionList(mToken, mSessionId, mGGSESSION, mNoGetRewardList)
                        }
                    }
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


    private suspend fun getMissionList(token:String?,session:String,mGGSESSION:String?) = getRetrofit().getMissList(cookie = "XSRF-TOKEN=${token}; GGSESSION=$mGGSESSION;sessionId=${session}; _ga=GA1.2.742200929.1606896379; _gid=GA1.2.1786067544.1608878855; _gat=1")

    private  fun finishMissionList(token:String?,session:String,mGGSESSION:String?,list:List<Int>){
        runBlocking {
            list.forEach {   getRetrofit().awardMissionList(
                    cookie = "XSRF-TOKEN=${token}; GGSESSION=$mGGSESSION;sessionId=${session}",
                    request = awardMissionRequest(it))
                    mOtherMessage.value = "完成成功"
            }

        }


    }
}