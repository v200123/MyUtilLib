package com.last_coffee.liubaselib

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：需要接入ViewBinding就实现这个接口
 * @Time: 十二月
 *
 **/
interface IBaseDataBind {

    fun initViewBinding(layoutInflater:LayoutInflater)

}