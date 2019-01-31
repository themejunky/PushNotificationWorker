package com.theme.junky.pushnotificationlib

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class ManagerPush {
    var nameLog = ""

    fun setPushNotification(isSilent:Boolean,title:String,body:String,icon:Int,time1Min:Long,time2Min:Long,time3Min:Long,nameLog:String){
        this.nameLog = nameLog
        Log.d(nameLog,"----setPushNotification----" )
        Log.d(nameLog,"isSilent: ${isSilent}, title: ${title}, body: ${body}, nameLog: ${nameLog}" )
        Log.d(nameLog,"the push notification will be shown in ${time1Min} min" )
        val data = Data.Builder()
            .putString("title",title)
            .putString("body",body)
            .putInt("icon",icon)
            .putBoolean("isSilent",isSilent)
            .putString("nameLog",nameLog)
            .build()
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        WorkManager.getInstance().beginUniqueWork("sync_push1", ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker::class.java).setConstraints(constraints).addTag("sync_push1").setInputData(data).setInitialDelay(time1Min, TimeUnit.SECONDS).build()).enqueue()
        WorkManager.getInstance().beginUniqueWork("sync_push2", ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push2").setInputData(data).setInitialDelay(time2Min, TimeUnit.SECONDS).build()).enqueue()
        WorkManager.getInstance().beginUniqueWork("sync_push3", ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker3::class.java).setConstraints(constraints).addTag("sync_push3").setInputData(data).setInitialDelay(time3Min, TimeUnit.SECONDS).build()).enqueue()

    }

    fun itIsPushShown(activity: Context):Boolean{
        val notificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var sad = arrayOfNulls<StatusBarNotification>(1)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            sad = notificationManager.activeNotifications
        }
        return if ( sad.size == 0) {
            Log.d(nameLog,"No push notififcation shown")
            false
        } else {
            Log.d(nameLog,"Push notififcation shown")
            true

        }
    }


}