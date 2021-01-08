package com.last_coffee.liubaselib.httpUtils

/**
 *@date: 2020/9/10
 *@describe:
 *@Auth: 29579
 **/
//定义网络请求状态(密封类扩展性更好)
sealed class StateActionEvent()

/**
 * 当type为0的时候则显示Loading图标，否则不显示
 */
class LoadState(val type:Int,val message: String) : StateActionEvent()

object SuccessState : StateActionEvent()

object NeedLoginState : StateActionEvent()

class ErrorState(val message: String?,val errorCode:Int = -1) : StateActionEvent()
