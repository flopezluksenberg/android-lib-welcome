package com.easychange.welcome.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easychange.commons.ui.WindowUtils
import com.easychange.welcome.R
import com.easychange.welcome.inflate
import com.easychange.welcome.ui.viewpager.IntroAdapter
import com.easychange.welcome.ui.viewpager.IntroPageTransformer
import com.easychange.welcome.ui.viewpager.StepPageChangeListener
import kotlinx.android.synthetic.main.wlc_fragment_welcome.button_fragmentwelcome_done as done
import kotlinx.android.synthetic.main.wlc_fragment_welcome.button_fragmentwelcome_next as next
import kotlinx.android.synthetic.main.wlc_fragment_welcome.button_fragmentwelcome_skip as skip
import kotlinx.android.synthetic.main.wlc_fragment_welcome.tablayout_fragmentwelcome_dots as tablayout
import kotlinx.android.synthetic.main.wlc_fragment_welcome.viewpager_fragmentwelcome as viewpager

class WelcomeFragment : Fragment(), StepPageChangeListener.OnPageChangeListener {
    companion object{
        fun newInstance(): WelcomeFragment{
            return WelcomeFragment()
        }
    }

    private val introAdapter: IntroAdapter by lazy { IntroAdapter(activity.supportFragmentManager) }
    private val colorEvaluator = android.animation.ArgbEvaluator()
    private lateinit var listener: Listener

    /*
     * Fragment Lifecycle methods
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as Listener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnArticleSelectedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.wlc_fragment_welcome)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        viewpager.adapter = introAdapter
        viewpager.addOnPageChangeListener(StepPageChangeListener(this));
        viewpager.setPageTransformer(false, IntroPageTransformer())
        tablayout.setupWithViewPager(viewpager, true)

        done.setOnClickListener { listener.onDoneButtonClicked() }
        next.setOnClickListener { listener.onNextButtonClicked() }
        skip.setOnClickListener { listener.onSkipButtonClicked() }
    }

    /*
     * WelcomeContract.View methods
     */

    fun showNextTab() {
        if (!isLastPage()) viewpager.setCurrentItem( viewpager.currentItem + 1, true)
    }

    fun hideNextButton() {
        next.visibility = View.GONE
    }

    fun showDoneButton() {
        done.visibility = View.VISIBLE
    }

    fun hideSkipButton() {
        skip.visibility = View.INVISIBLE
    }

    fun showNextButton() {
        next.visibility = View.VISIBLE
    }

    fun hideDoneButton() {
        done.visibility = View.GONE
    }

    fun showSkipButton() {
        skip.visibility = View.VISIBLE
    }

    fun isLastPage(): Boolean = (viewpager.adapter as IntroAdapter).isLastPage(viewpager.currentItem)
    fun getCurrentTab(): Int  = viewpager.currentItem


    /*
     * StepPageChangeListener methods
     */
    override fun onPageChange() {
        listener.onPageChange()
    }

    override fun onPageScrolled(position: Int, positionOffset: Float) {
        val adapter = viewpager.adapter as IntroAdapter
        val color: Int?

        if(position < adapter.count -1){
            color = colorEvaluator.evaluate(positionOffset, adapter.fragments[position].color, adapter.fragments[position + 1].color) as Int
            viewpager.setBackgroundColor(color)
        }else{
            color = adapter.fragments[position].color
            viewpager.setBackgroundColor(color)
        }

        WindowUtils.changeStatusBarColor(activity, color)
    }

    interface Listener {
        fun onPageChange()
        fun onDoneButtonClicked()
        fun onSkipButtonClicked()
        fun onNextButtonClicked()
    }
}