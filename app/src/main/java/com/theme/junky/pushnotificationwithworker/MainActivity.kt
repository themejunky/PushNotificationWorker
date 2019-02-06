package com.theme.junky.pushnotificationwithworker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.theme.junky.pushnotificationlib.ManagerPush
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButton.setOnClickListener {
            ManagerPush().itIsPushShown(this)
        }
       // ManagerPush().setPushNotificationDefault(this,true,"title","Body",R.mipmap.ic_launcher,10,20,0,"testPush")
        ManagerPush().setPushNotificationWithClass(this,true,"title","Body",R.mipmap.ic_launcher,10,20,0,"testPush",Main2Activity::class.java)
    }
}
