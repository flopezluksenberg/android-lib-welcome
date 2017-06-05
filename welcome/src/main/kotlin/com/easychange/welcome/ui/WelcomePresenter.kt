package com.easychange.welcome.ui

class WelcomePresenter : WelcomeContract.Presenter {
    private var view: WelcomeContract.View? = null

    override fun attach(view: WelcomeContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun initialize(currentTab: Int) {
        pageChanged()
    }

    override fun pageChanged() {
        view?.let {
            if (it.isLastPage()) {
                it.hideSkipButton()
                it.hideNextButton()
                it.showDoneButton()
                return
            }

            it.hideDoneButton()
            it.showSkipButton()
            it.showNextButton()
        }
    }

    override fun nextButtonClicked() {
        view?.showNextTab()
        pageChanged()
    }

    override fun skipButtonClicked() {
        view?.welcomeFinished()
    }

    override fun doneButtonClicked() {
        view?.welcomeFinished()
    }
}