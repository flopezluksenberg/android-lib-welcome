package com.easychange.welcome.ui


interface WelcomeContract {
    interface Presenter{
        fun attach(view: View)
        fun detach()
        fun initialize(currentTab: Int)
        fun doneButtonClicked()
        fun nextButtonClicked()
        fun skipButtonClicked()
        fun pageChanged()
    }

    interface View{
        fun hideNextButton()
        fun showNextButton()
        fun showDoneButton()
        fun hideDoneButton()
        fun hideSkipButton()
        fun showSkipButton()
        fun showNextTab()
        fun welcomeFinished()
        fun isLastPage(): Boolean
    }
}