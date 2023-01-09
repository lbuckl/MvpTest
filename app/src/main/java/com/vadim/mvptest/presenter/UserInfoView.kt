package com.vadim.mvptest.presenter

import com.vadim.mvptest.domain.GithubRepositoryEntity
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
    fun init()
    //Действие при начале загрузки
    fun startLoading()
    //Действие при окончании/прерывании загрузки
    fun endLoading()
    //Показать информацию о пользователе
    fun showBaseInformation(url: String?, name: String)
    //Показать информацию о пользователе
    fun showRepositoryInformation()
    //Действия при возникновении ошибоке
    fun showError(message: String)
    //Отображение детальной информации
    fun showDetails(repInfo: GithubRepositoryEntity)
}
