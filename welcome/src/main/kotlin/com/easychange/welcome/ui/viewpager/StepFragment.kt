package com.easychange.welcome.ui.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easychange.welcome.R
import com.easychange.welcome.inflate
import com.easychange.welcome.load
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlinx.android.synthetic.main.wlc_fragment_welcome_step.view.imageview_fragmentwelcomestep as imageview
import kotlinx.android.synthetic.main.wlc_fragment_welcome_step.view.textview_fragmentwelcomestep_description as description
import kotlinx.android.synthetic.main.wlc_fragment_welcome_step.view.textview_fragmentwelcomestep_title as title

class StepFragment : Fragment() {
    companion object {
        private val PAGE = "com.easychange.welcome.ui.viewpager.StepFragment.PAGE"
        private val COLOR = "com.easychange.welcome.ui.viewpager.StepFragment.COLOR"
        private val TITLE = "com.easychange.welcome.ui.viewpager.StepFragment.TITLE"
        private val DESCRIPTION = "com.easychange.welcome.ui.viewpager.StepFragment.DESCRIPTION"
        private val DRAWABLE = "com.easychange.welcome.ui.viewpager.StepFragment.DRAWABLE"
        private val LOGGER: Logger = LoggerFactory.getLogger(StepFragment::class.java)

        fun newInstance(page: Int, color: Int, drawable: Int, title: String, description: String): StepFragment {
            val fragment = StepFragment()
            val bundle = Bundle()
            bundle.putInt(PAGE, page)
            bundle.putInt(COLOR, color)
            bundle.putString(TITLE, title)
            bundle.putString(DESCRIPTION, description)
            bundle.putInt(DRAWABLE, drawable)
            fragment.arguments = bundle
            return fragment
        }
    }

    val position: Int by lazy { arguments.getInt(PAGE) }
    val title: String by lazy { arguments.getString(TITLE) }
    val description: String by lazy { arguments.getString(DESCRIPTION) }
    val drawable: Int by lazy { arguments.getInt(DRAWABLE) }
    val color: Int by lazy { arguments.getInt(COLOR) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LOGGER.info("Created step with values {position: $position, color: $color, title: $title, description: $description, drawable: $drawable}")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.wlc_fragment_welcome_step)

        view?.let {
            view.title.text = title
            view.imageview.load(drawable)
            view.description.text = description
            view.tag = position
        }

        return view
    }
}