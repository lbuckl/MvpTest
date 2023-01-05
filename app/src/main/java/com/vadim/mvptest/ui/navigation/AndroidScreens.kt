package com.vadim.mvptest.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vadim.mvptest.ui.UserInfoFragment
import com.vadim.mvptest.ui.UsersFragment

/**
 * Класс для объявления экранов в презентёре
 */
object AndroidScreens : IScreens {
    //Выполняет прикрепление фрагмента UsersFragment
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    //Выполняет прикрепление фрагмента UserInfoFragment
    override fun userInfo() = FragmentScreen { UserInfoFragment.newInstance() }
}