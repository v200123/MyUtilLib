package com.last_coffee.liubaselib

/**
 *
 * @PackAge：com.last_coffee.liubaselib
 * @创建人：admin
 * @Desc：
 * @Time: 十二月
 *
 **/
sealed class StateActionEvent

class LoadState(val type:Int = 0) : StateActionEvent()

object SuccessState : StateActionEvent()


class ErrorState(val message: String?,val errorCode:Int = -1) : StateActionEvent()