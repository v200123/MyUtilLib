package com.last_coffee.liubaselib

import androidx.lifecycle.ViewModel

/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
open class BaseViewModel<REP:BaseRepository>(var mRepository:REP) : ViewModel() {



}