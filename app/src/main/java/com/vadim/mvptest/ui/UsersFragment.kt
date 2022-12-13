package com.vadim.mvptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadim.mvptest.App
import com.vadim.mvptest.databinding.FragmentUsersBinding
import com.vadim.mvptest.model.GithubUsersRepo
import com.vadim.mvptest.presenter.UsersPresenter
import com.vadim.mvptest.presenter.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    //Создаём презентёр с cicerone навигацией
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router) }

    private var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    /**
     * Функция инициализации фрагмента
     */
    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    /**
     * Функция обновления данных в recyclerView
     */
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    /**
     * Функция нажатия кнопки "Назад"
     */
    override fun backPressed() = presenter.backPressed()
}