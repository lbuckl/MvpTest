package com.vadim.mvptest.ui

import com.vadim.mvptest.ui.userinfo.GithubRepositoryItemView
import com.vadim.mvptest.ui.userlist.UserItemView

/**
 * Общий интерфейс работы со скписком
 */
interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

/**
 * интерфейс работы со скписком юзеров
 */
interface IUserListPresenter : IListPresenter<UserItemView>

interface IGithubRepositoryListPresenter : IListPresenter<GithubRepositoryItemView>