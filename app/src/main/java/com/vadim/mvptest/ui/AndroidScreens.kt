package com.vadim.mvptest.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Класс для объявления экранов в презентёре
 */
class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}
