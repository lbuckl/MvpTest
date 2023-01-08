package com.vadim.mvptest.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.ui.userinfo.UserInfoFragment
import com.vadim.mvptest.ui.userlist.UsersFragment

/**
 * Класс для объявления экранов в презентёре
 */
object AndroidScreens : IScreens {
    //Выполняет прикрепление фрагмента UsersFragment
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    //Выполняет прикрепление фрагмента UserInfoFragment
    override fun userInfo(user: GithubUserEntity) = FragmentScreen { UserInfoFragment.newInstance(user) }
}
