package com.vadim.mvptest.ui

import com.github.terrakok.cicerone.Screen

/**
 * Интерфейс для объявления экранов в презентёре
 */
interface IScreens {
    fun users(): Screen
    fun userInfo(): Screen
}
