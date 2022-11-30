package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.GithubUsersRepo
import com.vadim.mvptest.ui.AndroidScreens
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.ui.UserItemView
import com.vadim.mvptest.ui.UsersView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
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
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
            Log.v("@@@","Click")
            router.navigateTo(AndroidScreens.userInfo())
        }
    }

    fun loadData() {
        val stringObserver = object : Observer<GithubUser> {
            //Параметр для отписки
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: GithubUser) {
                usersListPresenter.users.add(t)
                Log.v("@@@","OnNext: $t")
            }

            override fun onError(e: Throwable) {
                Log.v("@@@","Error")
            }

            override fun onComplete() {
                viewState.updateList()
                Log.v("@@@","Complete")
            }
        }

        usersRepo.fromIterable().subscribe(stringObserver)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
