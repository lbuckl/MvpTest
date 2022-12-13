package com.vadim.mvptest.presenter

import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.ui.navigation.AndroidScreens
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 * [backClicked] - действие при нажатии кнопки "Назад"
 */
class UserInfoPresenter(private val router: Router):
    MvpPresenter<UserInfoView>() {

    /**
     * Команда роутеру на действие "назад"
     */
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}