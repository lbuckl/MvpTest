package com.vadim.mvptest.ui

/**
 * Интерфейс для работы с элементом списка
 */
interface UserItemView: IItemView {
    //Функция устанавливает текст в элемент recyclerView
    fun setLogin(text: String, url: String)
}
