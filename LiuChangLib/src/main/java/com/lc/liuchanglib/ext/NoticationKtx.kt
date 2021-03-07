package com.lc.liuchanglib.ext

import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun (NotificationCompat.Builder).noticationCreate(context: Context, noticationGroup:List<NoticaionInfo>){
    val mNotificaManager = NotificationManagerCompat.from(context)
}

class NoticaionInfo(
    noticationId:String,noticationName:String,noticationImportan:Int
)