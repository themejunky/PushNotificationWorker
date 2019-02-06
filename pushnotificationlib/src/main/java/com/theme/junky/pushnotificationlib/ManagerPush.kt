package com.theme.junky.pushnotificationlib

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.service.notification.StatusBarNotification
import android.support.v4.app.NotificationCompat
import android.util.Log
import androidx.work.*
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class ManagerPush {
    var nameLog = ""
    var prefs: SharedPreferences? = null
    private lateinit var onNotificationTapIntent: Intent
    fun setPushNotificationWithClass(mActivity: Activity,isSilent: Boolean,title: String,body: String,icon: Int,time1Sec: Long,time2Sec: Long,time3Sec: Long,nameLog: String,className: Class<*>) {

        this.nameLog = nameLog
        prefs = mActivity.getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val editor = prefs!!.edit()

        onNotificationTapIntent = Intent()
        onNotificationTapIntent.setClass(mActivity, className)
        editor.putString(mActivity.resources.getString(R.string.pref_key_tapOnIntent), onNotificationTapIntent?.toURI())
        editor.apply()
        Log.d(nameLog, "----setPushNotification----")
        Log.d(nameLog, "isSilent: ${isSilent}, title: ${title}, body: ${body}, nameLog: ${nameLog}")
        Log.d(nameLog, "the push notification will be shown in ${time1Sec} sec")
        val data = Data.Builder()
            .putString("title", title)
            .putString("body", body)
            .putInt("icon", icon)
            .putBoolean("isSilent", isSilent)
            .putString("nameLog", nameLog)
            .build()
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        if (time3Sec > 0) {
            WorkManager.getInstance().beginUniqueWork("sync_push1",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push1").setInputData(data).setInitialDelay(time1Sec, TimeUnit.SECONDS).build()).enqueue()
            WorkManager.getInstance().beginUniqueWork("sync_push2",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push2").setInputData(data).setInitialDelay(time2Sec, TimeUnit.SECONDS).build()).enqueue()
            WorkManager.getInstance().beginUniqueWork("sync_push3",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push3").setInputData(data).setInitialDelay(time3Sec, TimeUnit.SECONDS).build()).enqueue()
        } else if (time3Sec == 0L) {
            WorkManager.getInstance().beginUniqueWork("sync_push1",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push1").setInputData(data).setInitialDelay(time1Sec, TimeUnit.SECONDS).build()).enqueue()
            WorkManager.getInstance().beginUniqueWork("sync_push2",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push2").setInputData(data).setInitialDelay(time2Sec, TimeUnit.SECONDS).build()).enqueue()
        }
    }

    fun itIsPushShown(activity: Context): Boolean {
        val notificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var sad = arrayOfNulls<StatusBarNotification>(1)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            sad = notificationManager.activeNotifications
        }
        return if (sad.size == 0) {
            Log.d(nameLog, "No push notififcation shown")
            false
        } else {
            Log.d(nameLog, "Push notififcation shown")
            true

        }
    }

    fun setPushNotificationDefault(mActivity: Activity,isSilent: Boolean,title: String,body: String,icon: Int,time1Sec: Long,time2Sec: Long,time3Sec: Long,nameLog: String) {

        this.nameLog = nameLog
        Log.d(nameLog, "----setPushNotification----")
        Log.d(nameLog, "isSilent: ${isSilent}, title: ${title}, body: ${body}, nameLog: ${nameLog}")
        Log.d(nameLog, "the push notification will be shown in ${time1Sec} sec")
        val data = Data.Builder()
            .putString("title", title)
            .putString("body", body)
            .putInt("icon", icon)
            .putBoolean("isSilent", isSilent)
            .putString("nameLog", nameLog)
            .build()
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        if (time3Sec > 0) {
            WorkManager.getInstance().beginUniqueWork("sync_push1",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push1").setInputData(data).setInitialDelay(time1Sec, TimeUnit.SECONDS).build()).enqueue()
            WorkManager.getInstance().beginUniqueWork("sync_push2",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push2").setInputData(data).setInitialDelay(time2Sec, TimeUnit.SECONDS).build()).enqueue()
            WorkManager.getInstance().beginUniqueWork("sync_push3",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push3").setInputData(data).setInitialDelay(time3Sec, TimeUnit.SECONDS).build()).enqueue()
        } else if (time3Sec == 0L) {
            WorkManager.getInstance().beginUniqueWork("sync_push1",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push1").setInputData(data).setInitialDelay(time1Sec, TimeUnit.SECONDS).build()).enqueue()
            WorkManager.getInstance().beginUniqueWork("sync_push2",ExistingWorkPolicy.KEEP,OneTimeWorkRequest.Builder(MyWorker2::class.java).setConstraints(constraints).addTag("sync_push2").setInputData(data).setInitialDelay(time2Sec, TimeUnit.SECONDS).build()).enqueue()
        }
    }

}