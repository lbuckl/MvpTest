package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.GithubRepositoryImpl
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.ui.navigation.AndroidScreens
import com.vadim.mvptest.ui.userlist.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

/**
 * Презенрёр связывает между собой модель и вью
 */
class UsersPresenter(
    private val usersRepo: GithubRepositoryImpl,
    private val router: Router
    ) :
    MvpPresenter<UsersView>() {

    private lateinit var disposable: Disposable

    /**
     * Класс для логики и наполенения view
     */
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUserEntity>()

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
        //Первичная инициализация
        viewState.init()

        //Загрузка списка юзеров
        loadData()

        //Кликкер по списку юзеров
        usersListPresenter.itemClickListener = {
            router.navigateTo(AndroidScreens.userInfo(usersListPresenter.users[it.pos]))
        }

        //Слежение за состоянием связи
        usersRepo.networkStatus.isOnline()
            .observeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isOnline ->
                if (!isOnline) viewState.error("Потеряна связь")
                else viewState.endLoading()
            }
    }

    // подписка на поток данных RxJava
    private fun loadData() {
        viewState.startLoading()
        //Делаем запрос пользователей с сайта ГитХаб
        disposable = usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
            {
                if (it.isEmpty()){
                    viewState.error("Проверьте интернет соединение")
                }
                else{
                    it.forEach{ user ->
                        usersListPresenter.users.add(user)
                    }
                    viewState.updateList()
                }
            },
            {
                Log.e("@@@",it.message.toString())
                viewState.error("Ошибка загрузки данных!")
            })
        viewState.endLoading()
    }

    fun backPressed(): Boolean {
        router.exit()
        disposable.dispose()
        return true
    }
}
