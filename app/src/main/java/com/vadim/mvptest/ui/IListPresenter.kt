package com.vadim.mvptest.ui

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