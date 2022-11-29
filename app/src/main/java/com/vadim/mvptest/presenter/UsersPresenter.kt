package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.GithubUsersRepo
import com.vadim.mvptest.ui.AndroidScreens
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.ui.UserItemView
import com.vadim.mvptest.ui.UsersView
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
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
