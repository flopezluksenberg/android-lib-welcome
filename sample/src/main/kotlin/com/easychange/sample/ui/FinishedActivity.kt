package com.easychange.sample.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.easychange.sample.R
import com.easychange.welcome.WelcomeApi
import kotlinx.android.synthetic.main.activity_finished.button_activityfinish_start as start
import kotlinx.android.synthetic.main.activity_finished.button_activityfinish_start_remember as startRemember

class FinishedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished)

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