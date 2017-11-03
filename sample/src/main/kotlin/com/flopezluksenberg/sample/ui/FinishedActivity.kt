package com.flopezluksenberg.sample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.flopezluksenberg.sample.R
import com.flopezluksenberg.welcome.WelcomeApi
import kotlinx.android.synthetic.main.activity_finished.button_activityfinish_start as start
import kotlinx.android.synthetic.main.activity_finished.button_activityfinish_start_remember as startRemember

class FinishedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished)

        start.setOnClickListener {
            WelcomeApi.start(showAlways = false)
        }

        startRemember.setOnClickListener {
            WelcomeApi.start()
        }
    }
}