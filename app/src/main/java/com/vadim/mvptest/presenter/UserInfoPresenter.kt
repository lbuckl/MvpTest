package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.repository.GithubRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 * [backClicked] - действие при нажатии кнопки "Назад"
 */
class UserInfoPresenter(
    private val user: GithubUserEntity,
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
        viewState.showBaseInformation(user.avatarUrl,user.login)

        if (user.repositoryUrl != null){
            disposable = usersRepo.getCustomInformation(user.repositoryUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        viewState.endLoading()
                        viewState.showRepositoryInformation(it)
                    },
                    {
                        Log.e("DevError",it.message.toString())
                        viewState.endLoading()
                        viewState.showError("Ошибка!!!")
                    })
        }
        else {
            viewState.endLoading()
            viewState.showError("Ошибка загрузки репозиториев")
        }

    }
}