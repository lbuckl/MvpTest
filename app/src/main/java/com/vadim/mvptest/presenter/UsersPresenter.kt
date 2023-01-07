package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.repository.GithubRepositoryImpl
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.ui.UserItemView
import com.vadim.mvptest.ui.navigation.AndroidScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class UsersPresenter(private val usersRepo: GithubRepositoryImpl, private val router: Router) :
    MvpPresenter<UsersView>() {

    private lateinit var disposable: Disposable

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
            router.navigateTo(AndroidScreens.userInfo(usersListPresenter.users[it.pos]))
        }
    }

    // подписка на поток данных RxJava
    private fun loadData() {
        //Делаем запрос пользователей с сайта ГитХаб
        disposable = usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                it.forEach{ user ->
                    usersListPresenter.users.add(user)
                }
                viewState.updateList()
            },
            {
                Log.e("DevError",it.message.toString())
                viewState.error("Ошибка!!!")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        disposable.dispose()
        return true
    }
}
