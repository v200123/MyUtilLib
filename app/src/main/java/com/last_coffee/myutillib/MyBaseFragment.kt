package com.last_coffee.myutillib

import androidx.viewbinding.ViewBinding
import com.kongzue.dialogx.dialogs.WaitDialog
import com.last_coffee.liubaselib.base.BaseFragment
import com.last_coffee.liubaselib.base.BaseViewModel
import com.tencent.bugly.proguard.T

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 一月
 *
 **/
abstract class MyBaseFragment<VM : BaseViewModel, T: ViewBinding> : BaseFragment<VM,T>() {
    override fun showLoadingDialog(message: String) {
        WaitDialog.show(message)
    }
    override fun getLoadingDialogText(): String =
            WaitDialog.getMessage().toString()


    override fun hideLoadingDialog() {
        WaitDialog.dismiss()
    }
}