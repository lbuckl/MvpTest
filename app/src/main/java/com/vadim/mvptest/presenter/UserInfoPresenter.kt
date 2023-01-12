package com.vadim.mvptest.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.GithubRepositoryImpl
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.ui.IGithubRepositoryListPresenter
import com.vadim.mvptest.ui.userinfo.GithubRepositoryItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
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
     * Класс для логики и наполенения view
     */
    class UsersListPresenter : IGithubRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepositoryEntity>()

        override var itemClickListener: ((GithubRepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: GithubRepositoryItemView) {
            val rep = repositories[view.pos]
            view.setRepositoryName(rep.name)
        }
    }

    val repositoryListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        //Первичная инициализация
        viewState.init()

        //Загрузка списка репоизториев
        loadData()

        //Кликкер по списку репозиториев
        repositoryListPresenter.itemClickListener = {
            viewState.showDetails(repositoryListPresenter.repositories[it.pos])
        }

        //Слежение за состоянием связи
        usersRepo.networkStatus.isOnline()
            .observeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isOnline ->
                if (!isOnline) viewState.showError("Потеряна связь")
                else viewState.endLoading()
            }
    }

    // подписка на поток данных RxJava
    private fun loadData() {
        viewState.startLoading()
        viewState.showBaseInformation(user.avatarUrl,user.login)

        if (user.repositoryUrl != null){
            disposable = usersRepo.getRepositoryInformation(user.id, user.repositoryUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        it.map { rep ->
                            repositoryListPresenter.repositories.add(rep)
                        }
                        viewState.endLoading()
                        viewState.showRepositoryInformation()
                    },
                    {
                        Log.e("DevError",it.message.toString())
                        viewState.endLoading()
                        viewState.showError("Ошибка!!!")
                    })
        }
        else {
            viewState.showError("Ошибка загрузки репозиториев")
        }
        viewState.endLoading()
    }

    /**
     * Команда роутеру на действие "назад"
     */
    fun backPressed(): Boolean {
        router.exit()
        disposable.dispose()
        return true
    }
}