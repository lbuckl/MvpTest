package com.vadim.mvptest.presenter

import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.MainView
import com.vadim.mvptest.ui.IScreens
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
