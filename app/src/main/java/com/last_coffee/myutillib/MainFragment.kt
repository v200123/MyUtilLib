package com.last_coffee.myutillib

import android.util.Log
import com.last_coffee.myutillib.databinding.ActivityMainBinding
import java.util.logging.Logger

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 一月
 *
 **/
class MainFragment : MyBaseFragment<MainViewModel,ActivityMainBinding>() {
    override fun startObserver() {

    }

    override fun initView() {
        Log.d(TAG, "initView: sdfsdfsdfdsfsd")
    }

    override fun initData() {
        mViewModel.getCheckStatue()
    }

    override fun initNeedRefreshData() {

    }

    override fun restoreData() {

    }

    override fun initOnClick() {

    }
}