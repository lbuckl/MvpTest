package com.vadim.mvptest.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadim.mvptest.App
import com.vadim.mvptest.databinding.FragmentUsersBinding
import com.vadim.mvptest.model.AndroidNetworkStatus
import com.vadim.mvptest.model.GithubRepositoryImpl
import com.vadim.mvptest.model.requests.NetworkProvider
import com.vadim.mvptest.presenter.UsersPresenter
import com.vadim.mvptest.presenter.UsersView
import com.vadim.mvptest.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Фрагмент для отображения списка пользователей GitHub
 */
class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    //Создаём презентёр с cicerone навигацией
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubRepositoryImpl(
                NetworkProvider.usersApi,
                App.getUserDB(requireContext()),
                AndroidNetworkStatus(requireContext())
            ),
            App.instance.router
        ) }

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

    override fun startLoading() {
        vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun endLoading() {
        vb?.progressBar?.visibility = View.GONE
    }

    /**
     * Функция обновления данных в recyclerView
     */
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    //Отображение ошибки
    override fun error(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }

    //Отображение информации
    override fun showInfo(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }


    //Функция нажатия кнопки "Назад"
    override fun backPressed() = presenter.backPressed()
}