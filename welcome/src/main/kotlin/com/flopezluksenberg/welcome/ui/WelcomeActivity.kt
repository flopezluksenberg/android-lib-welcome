package com.flopezluksenberg.welcome.ui

import android.animation.ArgbEvaluator
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.flopezluksenberg.welcome.R
import com.flopezluksenberg.welcome.ui.viewpager.IntroAdapter
import com.flopezluksenberg.welcome.ui.viewpager.IntroPageTransformer
import com.flopezluksenberg.welcome.ui.viewpager.StepPageChangeListener
import kotlinx.android.synthetic.main.wlc_activity_welcome.button_activitywelcome_done as done
import kotlinx.android.synthetic.main.wlc_activity_welcome.button_activitywelcome_next as next
import kotlinx.android.synthetic.main.wlc_activity_welcome.button_activitywelcome_skip as skip
import kotlinx.android.synthetic.main.wlc_activity_welcome.tablayout_activitywelcome_dots as tablayout
import kotlinx.android.synthetic.main.wlc_activity_welcome.viewpager_activitywelcome as viewpager


class WelcomeActivity : AppCompatActivity(), WelcomeContract.View, StepPageChangeListener.OnPageChangeListener{
    companion object {
        val WELCOME_FINISHED_EVENT = "com.flopezluksenberg.welcome.ui.WelcomeActivity.WELCOME_FINISHED_EVENT"
    }

    private val introAdapter: IntroAdapter by lazy { IntroAdapter(supportFragmentManager) }
    private val colorEvaluator = ArgbEvaluator()
    private val presenter: WelcomePresenter by lazy { WelcomePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wlc_activity_welcome)

        viewpager.adapter = introAdapter
        viewpager.addOnPageChangeListener(StepPageChangeListener(this))
        viewpager.setPageTransformer(false, IntroPageTransformer())
        tablayout.setupWithViewPager(viewpager, true)

        done.setOnClickListener { presenter.doneButtonClicked() }
        next.setOnClickListener { presenter.nextButtonClicked() }
        skip.setOnClickListener { presenter.skipButtonClicked() }
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.initialize(viewpager.currentItem)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun hideNextButton() {
        next.visibility = View.GONE
    }

    override fun showNextButton() {
        next.visibility = View.VISIBLE
    }

    override fun showDoneButton() {
        done.visibility = View.VISIBLE
    }

    override fun hideSkipButton() {
        skip.visibility = View.INVISIBLE
    }

    override fun showNextTab() {
        if (!isLastPage()) viewpager.setCurrentItem(viewpager.currentItem + 1, true)
    }

    override fun hideDoneButton() {
        done.visibility = View.GONE
    }

    override fun showSkipButton() {
        skip.visibility = View.VISIBLE
    }

    override fun isLastPage(): Boolean = (viewpager.adapter as IntroAdapter).isLastPage(viewpager.currentItem)

    override fun welcomeFinished() {
        val intent = Intent(WELCOME_FINISHED_EVENT)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        finish()
    }

    override fun onPageChange() {
        presenter.pageChanged()
    }

    override fun onPageScrolled(position: Int, positionOffset: Float) {
        val adapter = viewpager.adapter as IntroAdapter
        val color: Int?

        if (position < adapter.count - 1) {
            color = colorEvaluator.evaluate(positionOffset, adapter.fragments[position].color, adapter.fragments[position + 1].color) as Int
            viewpager.setBackgroundColor(color)
        } else {
            color = adapter.fragments[position].color
            viewpager.setBackgroundColor(color)
        }

        WindowUtils.changeStatusBarColor(this, color)
    }
}
