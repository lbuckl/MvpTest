package com.vadim.mvptest.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserInfoPresenter(private val router: Router): MvpPresenter<UserInfoView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}