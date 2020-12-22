package com.last_coffee.liubaselib

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.last_coffee.liubaselib.databinding.ActivityMainBinding


/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：封装了ViewBind的通用Activity类
 * @Time: 十二月
 *
 **/
abstract class BaseActivity<in T:ViewBinding>  : Activity(),IBaseActivityView,IBaseDataBind {
    private val mDataBindImpl:IBaseDataBind by lazy { this }
    private var mDataBinding:T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding =  mDataBindImpl.initViewBinding<T>(layoutInflater)
        initView()
        initData()
    }

    override fun <T : ViewBinding> initViewBinding(layoutInflater: LayoutInflater): T {
        ActivityMainBinding
    }
}