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
interface UserInfoView : MvpView {
    //Действие при начале загрузки
    fun startLoading()
    //Действие при окончании/прерывании загрузки
    fun endLoading()
    //Показать информацию о пользователе
    fun showInformation(url: String?, name: String, repositoryList: List<String>)
    //Действия при возникновении ошибоке
    fun showError(message: String)
}
