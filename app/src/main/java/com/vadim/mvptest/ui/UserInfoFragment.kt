package com.vadim.mvptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vadim.mvptest.App
import com.vadim.mvptest.R
import com.vadim.mvptest.databinding.FragmentUserInfoBinding
import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.repository.GithubRepositoryImpl
import com.vadim.mvptest.model.requests.NetworkProvider
import com.vadim.mvptest.presenter.UserInfoPresenter
import com.vadim.mvptest.presenter.UserInfoView
import com.vadim.mvptest.utils.loadImageFromUrl
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
        UserInfoPresenter(App.instance.router) }
    
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


    override fun startLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun endLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showInformation(url: String?, name: String, repositoryList: List<String>) {
        with(binding){
            if (url != null) userIcon.loadImageFromUrl(url)
            else userIcon.loadImageFromUrl(R.drawable.ic_user_placeholder)

            textViewUserInfoName.text = name
        }



    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}