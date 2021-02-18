package com.last_coffee.liubaselib.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.last_coffee.liubaselib.httpUtils.ErrorState
import com.last_coffee.liubaselib.httpUtils.LoadState
import com.last_coffee.liubaselib.httpUtils.SuccessState
import java.lang.reflect.ParameterizedType


/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：封装了带ViewBind功能的通用Activity类，如果你不需要则不用重写这个函数
 * @Time: 十二月
 *
 **/
abstract class BaseActivity<VM : BaseViewModel, T : ViewBinding> : AppCompatActivity(), IBaseActivityView {
    lateinit var mViewBinding: T
    //用于显示loading的计数
    @Volatile
    private var mLoadingCount: Int = 0
    lateinit var mViewModel: VM

    val mContext:Context by lazy { this }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = javaClass.genericSuperclass as ParameterizedType

        val clazz1 = type.actualTypeArguments[0] as Class<VM>
        mViewModel = ViewModelProvider(this).get(clazz1)


        val clazz2 = type.actualTypeArguments[1] as Class<T>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        mViewBinding = method.invoke(null, layoutInflater) as T
        initView()
        initData()
        startObserver()
        setContentView(mViewBinding.root)
        mViewModel.mStateLiveData.observe(this) {
            when (it) {
                is LoadState -> {
                    mLoadingCount += 1
                    if (it.type == 0) {
                        if (mLoadingCount == 1) {
                            showLoadingDialog(it.message)
                        }
                        if (getLoadingDialogText() != it.message) {
                            showLoadingDialog(it.message)
                        }
                    }
                }
                is SuccessState -> {
                    mLoadingCount -= 1
                    if (mLoadingCount == 0) {
                        hideLoadingDialog()
                    }
                }
                is ErrorState -> {
                    mLoadingCount -= 1
                    if (mLoadingCount == 0) {
                        hideLoadingDialog()
                    }
                    Log.d("Liuchang", "ErrorState：${it.message}")
                }
                else -> {

                }
            }
        }
    }

    abstract fun getLoadingDialogText(): String
    abstract fun showLoadingDialog(message: String)
    abstract fun hideLoadingDialog()
    abstract fun startObserver()
}