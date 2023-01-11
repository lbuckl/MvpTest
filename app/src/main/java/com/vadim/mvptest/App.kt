package com.vadim.mvptest

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.database.users.GithubAppDB
import com.vadim.mvptest.model.database.githubrepo.GithubRepoDB

class App : Application() {
    companion object {
        lateinit var instance: App

        private var userDB: GithubAppDB? = null
        private var userRepositoryDB: GithubRepoDB? = null

        //Создаём БД для GithubUsers
        fun getUserDB(context: Context): GithubAppDB {
            return if (userDB == null){
                Room.databaseBuilder(
                    context,
                    GithubAppDB::class.java,
                    GITHUB_USERS_DB_NAME
                ).build()
            } else{
                userDB ?: throw RuntimeException(
                    "Database has not been created. Please call create(context)")
            }
        }

        //Создаём БД для списков репозиториев пользователей Github
        fun getGithubRepoDB(context: Context): GithubRepoDB {
            return if (userRepositoryDB == null){
                Room.databaseBuilder(
                    context,
                    GithubRepoDB::class.java,
                    GITHUB_REPO_DB_NAME
                ).build()
            } else{
                userRepositoryDB ?: throw RuntimeException(
                    "Database has not been created. Please call create(context)")
            }
        }
    }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    // посредник между Navigator и CommandBuffer
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    //Роутер (генератор команд для навигатора)
    /**
     * 1) navigateTo(Screen) - переход на новый экран;
     * 2) newScreenChain(Screen) - сброс стека до корневого экрана и открытие одного нового;
     * 3) newRootScreen(Screen) - сброс стека и замена корневого экрана;
     * 4) replaceScreen(Screen) - замена активного экрана;
     * 5) backTo(Screen) - возврат на любой экран в стеке;
     * 6) exit() - выход с активного экрана.
     */
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}