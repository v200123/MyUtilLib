package com.lc.liuchanglib.init

import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 *
 * @PackAge：com.lc.liuchanglib.init
 * @创建人：Lc
 * @Desc：
 * @Time: 一月
 *
 **/
class LogInit {
    companion object{
        fun init(context: Context,tag:String = "com.lc.app",isShow:Boolean = true){
            com.orhanobut.logger.Logger.addLogAdapter(object : AndroidLogAdapter(){
                override fun isLoggable(priority: Int, tag: String?): Boolean {
                    return isShow
                }
            })
            PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(true)
                    .tag(tag)
                    .build()
        }

        fun showInfo(message:String){
            Logger.i(message)
        }

        fun showError(throwable: Throwable?,message:String){
            Logger.e(throwable,message)
        }
        fun showDebug(message:String){
            Logger.d(message)
        }
        fun showVerBose(message:String){
            Logger.v(message)
        }
    }
}