package com.vadim.mvptest.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс для связи презентёра с View
 * AddToEndSingleStrategy - добавит пришедшую команду в конец очереди команд.
 * Если команда такого типа уже есть в очереди, то действующая удалится
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    //Функция инициализации фрагмента
    fun init()
    //Функция обновления данных фрагмента
    fun updateList()
    //Действия при возникновении ошибок
    fun error(message:String)
    //Функция для информационных оповещений
    fun showInfo(message:String)
}
