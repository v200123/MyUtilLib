package com.last_coffee.liubaselib

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.last_coffee.liubaselib.databinding.ActivityMainBinding


/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：封装了带ViewBind功能的通用Activity类，如果你不需要则不用重写这个函数
 * @Time: 十二月
 *
 **/
abstract class BaseActivity<VM : BaseViewModel, T:ViewBinding>  : Activity(),IBaseActivityView {
    private val mDataBinding:T? by lazy { initViewBinding() }

    lateinit var mViewModel:VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    abstract fun initViewBinding():T


}