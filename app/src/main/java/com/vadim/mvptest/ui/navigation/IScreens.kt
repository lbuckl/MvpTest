package com.vadim.mvptest.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.vadim.mvptest.model.GithubUserEntity

/**
 * Интерфейс хранит вызов экранов в виде функций
 */
interface IScreens {
    fun users(): Screen
    fun userInfo(user: GithubUserEntity): Screen
}
