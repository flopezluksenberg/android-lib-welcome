package com.easychange.welcome.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.easychange.welcome.R
import com.easychange.welcome.addFragment
import kotlinx.android.synthetic.main.wlc_activity_welcome.framelayout_activitywelcome as framelayout


class WelcomeActivity : AppCompatActivity(), WelcomeContract.View, WelcomeFragment.Listener {
    companion object {
        val WELCOME_FINISHED_EVENT = "com.easychange.welcome.ui.WelcomeActivity.WELCOME_FINISHED_EVENT"
    }

    private val presenter: WelcomePresenter by lazy { WelcomePresenter() }

    /*
     * Lifecycle methods
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wlc_activity_welcome)
        val welcomeFragment = supportFragmentManager.findFragmentById(R.id.framelayout_activitywelcome)

        if (welcomeFragment == null)
            supportFragmentManager.addFragment(R.id.framelayout_activitywelcome, WelcomeFragment.newInstance())
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.initialize(getWelcomeFragment().getCurrentTab())
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    /*
     * Helpers methods
     */

    fun getWelcomeFragment(): WelcomeFragment {
        return supportFragmentManager.findFragmentById(R.id.framelayout_activitywelcome) as WelcomeFragment
    }

    /*
     * WelcomeContract.View methods
     */
    override fun hideNextButton() {
        getWelcomeFragment().hideNextButton()
    }

    override fun showNextButton() {
        getWelcomeFragment().showNextButton()
    }

    override fun showDoneButton() {
        getWelcomeFragment().showDoneButton()
    }

    override fun hideSkipButton() {
        getWelcomeFragment().hideSkipButton()
    }

    override fun showNextTab() {
        getWelcomeFragment().showNextTab()
    }

    override fun hideDoneButton() {
        getWelcomeFragment().hideDoneButton()
    }

    override fun showSkipButton() {
        getWelcomeFragment().showSkipButton()
    }

    override fun isLastPage(): Boolean {
        return getWelcomeFragment().isLastPage()
    }

    override fun welcomeFinished() {
        val intent = Intent(WELCOME_FINISHED_EVENT)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        finish()
    }

    /*
     * WelcomeFragment.Listener methods
     */
    override fun onPageChange() {
        presenter.pageChanged()
    }

    override fun onDoneButtonClicked() {
        presenter.doneButtonClicked()
    }

    override fun onSkipButtonClicked() {
        presenter.skipButtonClicked()
    }

    override fun onNextButtonClicked() {
        presenter.nextButtonClicked()
    }
}
