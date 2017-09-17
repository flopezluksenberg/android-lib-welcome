package com.flopezluksenberg.sample.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.flopezluksenberg.sample.R
import com.flopezluksenberg.welcome.WelcomeApi
import kotlinx.android.synthetic.main.activity_main.button_activitymain_start as start
import kotlinx.android.synthetic.main.activity_main.button_activitymain_start_remember as startRemember

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FinishedActivity::class.java)

        start.setOnClickListener {
            finish()
            WelcomeApi.start(intent)
        }

        startRemember.setOnClickListener {
            finish()
            WelcomeApi.start(intent, onlyFirstOpenApp = true)
        }
    }


}