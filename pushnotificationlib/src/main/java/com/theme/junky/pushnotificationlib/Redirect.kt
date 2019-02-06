package com.theme.junky.pushnotificationlib

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class Redirect : AppCompatActivity() {
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("testPush","onCreate")

        prefs = getSharedPreferences("my_app", Context.MODE_PRIVATE)
        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=${application.packageName}")))
        finish()
        val editor = prefs.edit()
        editor.putBoolean("worker_complete",true)
        editor.apply()
    }


}