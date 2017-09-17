package com.flopezluksenberg.welcome.ui.viewpager

import android.support.v4.view.ViewPager


class StepPageChangeListener(val onPageChangeListener: OnPageChangeListener) : ViewPager.OnPageChangeListener {
    private var first: Boolean = true

    override fun onPageSelected(position: Int) {
        onPageChangeListener.onPageChange()
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (first && positionOffset == 0f && positionOffsetPixels == 0) {
            onPageSelected(0)
            first = false
        }
        onPageChangeListener.onPageScrolled(position, positionOffset)
    }


    interface OnPageChangeListener {
        fun onPageChange()
        fun onPageScrolled(position: Int, positionOffset: Float)
    }
}