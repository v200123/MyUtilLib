package com.last_coffee.myutillib

import com.last_coffee.myutillib.databinding.ActivityMainBinding

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