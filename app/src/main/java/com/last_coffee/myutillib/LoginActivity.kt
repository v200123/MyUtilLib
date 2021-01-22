package com.last_coffee.myutillib

import android.webkit.WebChromeClient
import androidx.activity.viewModels
import com.last_coffee.myutillib.databinding.ActivityLoginBinding

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 十二月
 *
 **/
class LoginActivity : MyBaseActivity<LoginViewModel,ActivityLoginBinding>() {
    private val mMyMViewModel by viewModels<LoginViewModel>()

    override fun initData() {
        mViewModel =mMyMViewModel
    }

    override fun initNeedRefreshData() {

    }

    override fun initView() {
        mViewBinding.wvLogin.settings.apply {
            domStorageEnabled = false
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            databaseEnabled = true
        }
        mViewBinding.wvLogin.apply {

        }
        mViewBinding.wvLogin.loadUrl("https://id.realme.com/index.html?language=zh-CN&callback=https%3A%2F%2Fid.realme.com%2Fprofile.html")
        mViewBinding.wvLogin.webChromeClient = WebChromeClient()
        mViewBinding.wvLogin.webViewClient = MyWebViewClient()

    }

    override fun restoreData() {

    }

    override fun initOnClick() {
        TODO("Not yet implemented")
    }

    override fun startObserver() {

    }

}