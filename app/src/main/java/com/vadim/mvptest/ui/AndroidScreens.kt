package com.vadim.mvptest.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Класс для объявления экранов в презентёре
 */
object AndroidScreens : IScreens {
    //Выполняет прикрепление фрагмента UsersFragment
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    //Выполняет прикрепление фрагмента UserInfoFragment
    override fun userInfo() = FragmentScreen { UserInfoFragment.newInstance() }
}
