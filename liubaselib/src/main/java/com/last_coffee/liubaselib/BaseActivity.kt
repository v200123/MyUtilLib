package com.last_coffee.liubaselib

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.last_coffee.liubaselib.databinding.ActivityMainBinding
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass


/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：封装了带ViewBind功能的通用Activity类，如果你不需要则不用重写这个函数
 * @Time: 十二月
 *
 **/
abstract class BaseActivity<VM : BaseViewModel, T:ViewBinding>  : AppCompatActivity(),IBaseActivityView {
     lateinit var mDataBinding:T

    lateinit var mViewModel:VM
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
        mViewModel.mStateLiveData.observe(this){
            when(it){
                is ErrorState -> {
                    Log.d("Liuchang", "ErrorState：${it.message}")
                }
            }
        }
    }



    abstract fun startObserver()
}