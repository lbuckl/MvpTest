package com.vadim.mvptest

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.vadim.mvptest.databinding.ActivityMainBinding
import com.vadim.mvptest.presenter.MainPresenter
import com.vadim.mvptest.ui.AndroidScreens
import com.vadim.mvptest.ui.BackButtonListener
import com.vadim.mvptest.ui.UsersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

/**
 * Основное активити:
 * Отправляет задачу презентёру [presenter]
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router,AndroidScreens())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }
    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }

}