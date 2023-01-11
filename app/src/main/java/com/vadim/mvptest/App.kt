package com.vadim.mvptest

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.vadim.mvptest.model.database.GithubAppDB

class App : Application() {
    companion object {
        lateinit var instance: App

        private var userDB: GithubAppDB? = null

        fun getUserDB(context: Context): GithubAppDB{
            return if (userDB == null){
                Log.v("@@@","Create DB")
                Room.databaseBuilder(
                    context,
                    GithubAppDB::class.java,
                    GITHUB_DB_NAME
                ).build()
            } else{
                Log.v("@@@","DB already created")
                userDB ?: throw RuntimeException(
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