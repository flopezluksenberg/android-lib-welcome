package com.flopezluksenberg.sample

import android.app.Application
import android.support.v4.content.ContextCompat
import com.flopezluksenberg.welcome.WelcomeApi
import com.flopezluksenberg.welcome.WelcomeStep

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        WelcomeApi.init(this, createWelcomeSteps())
    }

    private fun createWelcomeSteps(): Array<WelcomeStep> {
        return arrayOf(
                WelcomeStep(
                        getString(R.string.step_1_title),
                        getString(R.string.step_1_description),
                        ContextCompat.getColor(this, android.R.color.holo_blue_dark),
                        R.drawable.ic_placeholder_svg
                ),
                WelcomeStep(
                        getString(R.string.step_2_title),
                        getString(R.string.step_2_description),
                        ContextCompat.getColor(this, android.R.color.holo_green_dark),
                        R.drawable.ic_placeholder
                ),
                WelcomeStep(
                        getString(R.string.step_3_title),
                        getString(R.string.step_3_description),
                        ContextCompat.getColor(this, android.R.color.holo_red_dark),
                        R.drawable.ic_placeholder_svg
                )
        )
    }
}