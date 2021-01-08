package com.last_coffee.myutillib

import com.last_coffee.liubaselib.BaseViewModel
import com.tencent.bugly.proguard.T

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 一月
 *
 **/
open class MyBaseViewModel<U> : BaseViewModel<BaseRepose<Any>>() {


    override fun <V> BaseRepose<Any>.checkResult(): V? {
        if(this.mStatus == "success")
        {
            return this.mData as V?
        }
        return null
    }


}