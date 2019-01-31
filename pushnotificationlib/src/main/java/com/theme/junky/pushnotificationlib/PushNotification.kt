package com.theme.junky.pushnotificationlib

import android.app.*
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log

class PushNotification {
     fun sendNotification(activity: Context, isSilent: Boolean, icon: Int, title:String, body:String ) {

        val notificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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
        val mBuilder = NotificationCompat.Builder(activity, channelId)
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
        val stackBuilder = TaskStackBuilder.create(activity)
         val mRedirect = Intent(activity.applicationContext, Redirect::class.java)
         mRedirect.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                 Intent.FLAG_ACTIVITY_CLEAR_TASK or
                 Intent.FLAG_ACTIVITY_NEW_TASK
        stackBuilder.addNextIntent(mRedirect)
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)

        notificationManager.notify(notificationId, mBuilder.build())

    }
}