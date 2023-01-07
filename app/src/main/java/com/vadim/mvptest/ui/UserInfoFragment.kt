package com.vadim.mvptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vadim.mvptest.App
import com.vadim.mvptest.databinding.FragmentUserInfoBinding
import com.vadim.mvptest.model.repository.GithubRepositoryImpl
import com.vadim.mvptest.model.requests.NetworkProvider
import com.vadim.mvptest.presenter.UserInfoPresenter
import com.vadim.mvptest.presenter.UserInfoView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Фрагмент для отображения подробной информации о пользователе
 */
class UserInfoFragment: MvpAppCompatFragment(),UserInfoView,BackButtonListener {

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    //Создаём презентёр с cicerone навигацией
    private val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(GithubRepositoryImpl(NetworkProvider.usersApi), App.instance.router) }
    
    private var _binding: FragmentUserInfoBinding? = null
    private val binding: FragmentUserInfoBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun backPressed() = presenter.backPressed()
}