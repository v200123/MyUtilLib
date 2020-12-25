package com.last_coffee.myutillib

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.last_coffee.liubaselib.BaseViewModel
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
    val mCheckInStatueData = MutableLiveData<CheckInStatueBean>()
    fun getCheckStatue(){
        launchTask {

                val checkInStatue = getRetrofit().getCheckInStatue()
                if(checkInStatue.mCode == 0)
                {
                    mCheckInStatueData.postValue(checkInStatue.mData!!)
                }
                delay(5000)
                Log.d("LiuChang", "发起下一次请求：${System.currentTimeMillis()}")


        }
    }

}