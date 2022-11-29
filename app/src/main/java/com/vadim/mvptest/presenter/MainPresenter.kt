package com.vadim.mvptest.presenter

import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.MainView
import com.vadim.mvptest.ui.AndroidScreens
import com.vadim.mvptest.ui.IScreens
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 * [onFirstViewAttach] - выполняет действие при первом присоединении View
 * [backClicked]
 */
class MainPresenter(private val router: Router) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.users())
    }

    /**
     * Команда роутеру на действие "назад"
     */
    fun backClicked() {
        router.exit()
    }
}
