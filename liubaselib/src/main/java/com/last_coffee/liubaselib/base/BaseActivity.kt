package com.last_coffee.liubaselib.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
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
    lateinit var mDataBinding: T
    @Volatile
    private var mLoadingCount: Int = 0
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz2 = type.actualTypeArguments[1] as Class<T>
        Log.d("Liuchang", "onCreate: clazz2是啥${clazz2.name}")
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        mDataBinding = method.invoke(null, layoutInflater) as T
        initView()
        initData()
        startObserver()
        setContentView(mDataBinding.root)
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