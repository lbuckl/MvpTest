package com.vadim.mvptest.ui.userinfo

import com.vadim.mvptest.ui.IItemView

/**
 * Интерфейс для работы с элементом списка
 */
interface GithubRepositoryItemView: IItemView {
    //Функция устанавливает текст в элемент recyclerView
    fun setRepositoryName(text: String)
}
