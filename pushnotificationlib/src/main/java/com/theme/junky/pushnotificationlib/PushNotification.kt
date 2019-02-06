package com.theme.junky.pushnotificationlib

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.NotificationCompat
import android.util.Log

class PushNotification {
    private var onNotificationTapIntent: Intent? = null
    lateinit var prefs: SharedPreferences
     fun sendNotification(nContext: Context, isSilent: Boolean, icon: Int, title:String, body:String) {
         prefs = nContext.getSharedPreferences("my_app", Context.MODE_PRIVATE)
         onNotificationTapIntent = Intent.getIntent(prefs.getString(nContext.getString(R.string.pref_key_tapOnIntent),""))
        // onNotificationTapIntent.putExtra("isFromPush",true)
         (onNotificationTapIntent as Intent).putExtra("isFromPush",true)
         val notificationManager = nContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "channel-01"
        val channelName = "Channel Name1"
        var importance = NotificationManager.IMPORTANCE_HIGH
        if (isSilent) {
            importance = NotificationManager.IMPORTANCE_LOW
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
        }
        Log.d("aefasdf", "1")
        val mBuilder = NotificationCompat.Builder(nContext, channelId)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setWhen(System.currentTimeMillis())
            .setContentText(body)
            .setAutoCancel(true)
        if (isSilent) {
            mBuilder.setDefaults(Notification.DEFAULT_LIGHTS)
        } else {
            mBuilder.setDefaults(Notification.DEFAULT_ALL)
        }
         if(prefs.getString(nContext.getString(R.string.pref_key_tapOnIntent),"").equals("")){
             Log.d("Asfdasd","este null")
             val stackBuilder = TaskStackBuilder.create(nContext)
             val mRedirect = Intent(nContext.applicationContext, Redirect::class.java)

             mRedirect.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                     Intent.FLAG_ACTIVITY_CLEAR_TASK or
                     Intent.FLAG_ACTIVITY_NEW_TASK
             stackBuilder.addNextIntent(mRedirect)
             val resultPendingIntent = stackBuilder.getPendingIntent(
                 0,
                 PendingIntent.FLAG_UPDATE_CURRENT
             )
             mBuilder.setContentIntent(resultPendingIntent)
         }else{
             Log.d("Asfdasd","Nu este null")
             val stackBuilder = TaskStackBuilder.create(nContext)
             stackBuilder.addNextIntent(onNotificationTapIntent)
             val resultPendingIntent = stackBuilder.getPendingIntent(
                 0,
                 PendingIntent.FLAG_UPDATE_CURRENT
             )
             mBuilder.setContentIntent(resultPendingIntent)
         }



        notificationManager.notify(notificationId, mBuilder.build())

    }
}