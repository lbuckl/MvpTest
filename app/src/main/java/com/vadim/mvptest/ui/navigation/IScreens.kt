package com.vadim.mvptest.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.vadim.mvptest.model.GithubUser

/**
 * Интерфейс хранит вызов экранов в виде функций
 */
interface IScreens {
    fun users(): Screen
    fun userInfo(): Screen
}
