package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.repository.GithubRepositoryImpl
import com.vadim.mvptest.model.repository.GithubUsersRepo
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.ui.UserItemView
import com.vadim.mvptest.ui.navigation.AndroidScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class UsersPresenter(private val usersRepo: GithubRepositoryImpl, private val router: Router) :
    MvpPresenter<UsersView>() {

    /**
     * Класс для логики и наполенения view
     */
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login, user.avatarUrl)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
            router.navigateTo(AndroidScreens.userInfo())
        }
    }

    // подписка на поток данных RxJava
    private fun loadData() {

        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                it.forEach{ user ->
                    usersListPresenter.users.add(user)
                }
                viewState.updateList()
            },
            {
                viewState.error()
            })

        //viewState.updateList()
        /*val stringObserver = object : Observer<GithubUser> {
            //Параметр для отписки
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: GithubUser) {
                usersListPresenter.users.add(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
                viewState.updateList()
            }
        }

        //подписка на данные из репозитория
        usersRepo.fromIterable().subscribe(stringObserver)*/
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
