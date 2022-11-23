package com.vadim.mvptest.presenter

import com.vadim.mvptest.model.CountersModel
import com.vadim.mvptest.MainView
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class MainPresenter(private val model: CountersModel): MvpPresenter<MainView>() {

    fun counterOneClick() {
        val nextValue = model.next(0)
        viewState.setButtonOneText(nextValue.toString())
    }

    fun counterTwoClick() {
        val nextValue = model.next(1)
        viewState.setButtonTwoText(nextValue.toString())
    }

    fun counterThreeClick() {
        val nextValue = model.next(2)
        viewState.setButtonThreeText(nextValue.toString())
    }
}