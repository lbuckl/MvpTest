package com.vadim.mvptest.presenter

import com.vadim.mvptest.model.CountersModel
import com.vadim.mvptest.MainView
import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.GithubUsersRepo
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.ui.UserItemView
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class MainPresenter(private val usersRepo: GithubUsersRepo): MvpPresenter<MainView>() {

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
        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

}