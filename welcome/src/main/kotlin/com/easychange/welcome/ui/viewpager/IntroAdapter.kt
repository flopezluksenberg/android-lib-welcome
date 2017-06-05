package com.easychange.welcome.ui.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.easychange.welcome.WelcomeApi


class IntroAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    val fragments: Array<StepFragment> by lazy {
        WelcomeApi.steps.mapIndexed { index, (title, description, backgroundColor, imageResourceId) ->
            StepFragment.newInstance(index, backgroundColor, imageResourceId, title, description)
        }.toTypedArray()
    }


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }


    fun isLastPage(currentPosition: Int): Boolean {
        return fragments.size - 1 == currentPosition
    }
}