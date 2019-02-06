package com.theme.junky.pushnotificationwithworker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.theme.junky.pushnotificationlib.ManagerPush

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        ManagerPush().closePush(this)
    }
}
