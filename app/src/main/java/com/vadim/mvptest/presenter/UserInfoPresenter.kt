package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.repository.GithubRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 * [backClicked] - действие при нажатии кнопки "Назад"
 */
class UserInfoPresenter(
    private val usersRepo: GithubRepositoryImpl,
    private val router: Router):
    MvpPresenter<UserInfoView>() {

    private lateinit var disposable: Disposable
    /**
     * Команда роутеру на действие "назад"
     */
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.startLoading()

        loadData()
    }

    // подписка на поток данных RxJava
    private fun loadData() {

        //Делаем запрос пользователей с сайта ГитХаб
        disposable = usersRepo.getUserById()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.endLoading()
                    viewState.showInformation(it.avatarUrl,it.login, listOf())
                },
                {
                    Log.e("DevError",it.message.toString())
                    viewState.showError("Ошибка!!!")
                })
    }
}