package com.vadim.mvptest

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadim.mvptest.databinding.ActivityMainBinding
import com.vadim.mvptest.model.CountersModel
import com.vadim.mvptest.model.GithubUsersRepo
import com.vadim.mvptest.presenter.MainPresenter
import com.vadim.mvptest.ui.UsersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

/**
 * Основное активити:
 * Отправляет задачу презентёру [presenter]
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null

    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}