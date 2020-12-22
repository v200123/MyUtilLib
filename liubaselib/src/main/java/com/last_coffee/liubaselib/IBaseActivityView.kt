package com.last_coffee.liubaselib

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
 interface IBaseActivityView {
    /**
     * 初始化布局
     */
    fun initView()

    /**
     * 初始化需要的数据，注意，这里的数据不包含切换后要刷新的数据
     */
    fun initData()

    /**
     * 初始化需要的数据，注意，这里的数据是切换视图后要刷新的数据
     */
    fun initNeedRefreshData()
    fun restoreData()


}