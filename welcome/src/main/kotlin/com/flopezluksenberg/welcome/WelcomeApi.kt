package com.flopezluksenberg.welcome

import android.content.Context
import android.content.Intent
import com.flopezluksenberg.welcome.ui.WelcomeActivity


object WelcomeApi {
    private val IS_FIRST_OPEN_APP = "com.flopezluksenberg.welcome.WelcomeApi.IS_FIRST_OPEN_APP"

    lateinit var steps: Array<WelcomeStep>
    lateinit var context: Context

    fun init(context: Context, steps: Array<WelcomeStep>) {
        if (steps.isEmpty()) {
            throw IllegalStateException("At least one step is required!")
        }
        this.context = context
        this.steps = steps
    }

    fun start(showAlways: Boolean = false) {
        if (showAlways || isFirstOpenApp()) {
            val intent = Intent(context, WelcomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun isFirstOpenApp(): Boolean {
        if (PrefUtils.getFromPrefs(context, IS_FIRST_OPEN_APP, true)) {
            PrefUtils.saveToPrefs(context, IS_FIRST_OPEN_APP, false)
            return true
        }
        return false
    }
}