package com.easychange.welcome

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.easychange.welcome.ui.WelcomeActivity


object WelcomeApi{
    lateinit var steps: Array<WelcomeStep>
    lateinit var context : Context

    fun init(context: Context, steps: Array<WelcomeStep>){
        if(steps.isEmpty()){
            throw IllegalStateException("At least one step is required!")
        }
        this.context = context
        this.steps = steps
    }

    fun start(nextIntent: Intent){
        val intent = Intent(context, WelcomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        LocalBroadcastManager.getInstance(context).registerReceiver(onWelcomeActivityFinished(nextIntent), IntentFilter(WelcomeActivity.WELCOME_FINISHED_EVENT))
    }

    private fun onWelcomeActivityFinished(nextIntent: Intent): BroadcastReceiver? {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(nextIntent)
            }
        }
    }
}