package com.theme.junky.pushnotificationlib

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker2(var nContext: Context, nWorkerParameters: WorkerParameters) : Worker(nContext, nWorkerParameters) {

    lateinit var prefs: SharedPreferences

    override fun doWork(): Result {

        val title = inputData.getString("title")
        val nameLog = inputData.getString("nameLog")
        val body = inputData.getString("body")
        val icon = inputData.getInt("icon", 0)
        val isSilent = inputData.getBoolean("isSilent", true)
        Log.d(nameLog,"----doWork 2----" )
        prefs = nContext.getSharedPreferences("my_app", Context.MODE_PRIVATE)
        Log.d(nameLog,"----doWork 2---- ${prefs.getBoolean("worker_complete", false)}" )
        if (!prefs.getBoolean("worker_complete", false)) {
            if (body != null && title != null) {
                if(!ManagerPush().itIsPushShown(nContext)){
                    Log.d(nameLog,"show push notification 2" )
                    PushNotification().sendNotification(nContext, isSilent, icon, title, body)
                }
            }
        }
        return Result.success()
    }
}