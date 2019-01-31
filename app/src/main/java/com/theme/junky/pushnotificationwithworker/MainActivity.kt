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
        ManagerPush().setPushNotification(true,"title","Body",R.mipmap.ic_launcher,5,20,40,"testPush")
    }
}
