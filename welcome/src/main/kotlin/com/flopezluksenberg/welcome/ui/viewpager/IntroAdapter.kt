package com.flopezluksenberg.welcome.ui.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.flopezluksenberg.welcome.WelcomeApi


class IntroAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    val fragments: Array<StepFragment> by lazy {
        WelcomeApi.steps.mapIndexed { index, (title, description, backgroundColor, imageResourceId) ->
            StepFragment.newInstance(index, backgroundColor, imageResourceId, title, description)
        }.toTypedArray()
    }


    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size


    fun isLastPage(currentPosition: Int): Boolean = fragments.size - 1 == currentPosition
}