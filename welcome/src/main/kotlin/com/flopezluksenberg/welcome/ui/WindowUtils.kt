package com.flopezluksenberg.welcome.ui

import android.content.Context
import android.os.Build
import android.support.v4.view.WindowCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

object WindowUtils {
    fun changeStatusBarColor(context: Context, color: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = (context as AppCompatActivity).window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }
}