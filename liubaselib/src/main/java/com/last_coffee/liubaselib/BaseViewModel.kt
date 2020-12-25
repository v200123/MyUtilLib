package com.last_coffee.liubaselib

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

typealias LaunchBlock = suspend CoroutineScope.() -> Unit
typealias Cancel = (e: Exception) -> Unit
/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<StateActionEvent>()
    fun launchTask(cancel: Cancel? = { mStateLiveData.postValue(ErrorState("请求取消")) },
                   block: LaunchBlock){
        viewModelScope.launch {
            //ViewModel自带的viewModelScope.launch,会在页面销毁的时候自动取消请求,有效封装内存泄露
            mStateLiveData.value = LoadState()
            runCatching {
                block()
            }
                    .onSuccess {
                        mStateLiveData.value = SuccessState
                    }
                    .onFailure {
                        getApiException(it, cancel)
                    }
        }
    }

    private fun getApiException(e: Throwable, cancel: Cancel?) {
        when (e) {
            is UnknownHostException -> {
                mStateLiveData.value = ErrorState("网络异常", -100)
            }
            is JSONException -> {//|| e is JsonParseException
                mStateLiveData.value = ErrorState("数据异常", -100)
            }
            is SocketTimeoutException -> {
                mStateLiveData.value = ErrorState("连接超时", -100)
            }
            is ConnectException -> {
                mStateLiveData.value = ErrorState("连接错误", -100)
            }
//            is HttpException -> {
//                mStateLiveData.value = ErrorState("http code ${e.code()}", -100)
//            }
            /**
             * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
             * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
             */
            is CancellationException -> {
                cancel?.invoke(e)
            }
            else -> {
                mStateLiveData.value = ErrorState("Cookie失效", -100)
            }
        }
    }


}