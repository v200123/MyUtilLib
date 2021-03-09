package com.lc.liuchanglib.ext

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import java.util.ArrayList

fun notificationCreate(context: Context, notificationGroup:List<NotificationInfo>? = null):NotificationManagerCompat{
    val mNotificatManager = NotificationManagerCompat.from(context)
    val mNotificationList = ArrayList<NotificationChannel>()
    if(!notificationGroup.isNullOrEmpty()){
        notificationGroup.forEach {info->
            val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(info.notificationId, info.notificationName, info.notificationImportance)
            } else {
                throw IllegalArgumentException("为了适配Android 8，请填入你的通知渠道")
            }
            info.desc ?.let { notificationChannel.description = it }
            mNotificationList.add(notificationChannel)
        }
        mNotificatManager.createNotificationChannels(mNotificationList)
    }
    return mNotificatManager
}

fun NotificationManagerCompat.showNotification(notificationId:Int,notification: Notification){
    this.notify(notificationId,notification)
}


class NotificationInfo(
   var  notificationId:String,var notificationName:String,var notificationImportance:Int,var desc:String? = null
)