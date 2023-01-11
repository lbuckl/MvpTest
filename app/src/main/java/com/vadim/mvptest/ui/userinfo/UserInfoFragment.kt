package com.vadim.mvptest.ui.userinfo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.vadim.mvptest.App
import com.vadim.mvptest.R
import com.vadim.mvptest.databinding.FragmentUserInfoBinding
import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.AndroidNetworkStatus
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.GithubRepositoryImpl
import com.vadim.mvptest.model.database.GithubAppDB
import com.vadim.mvptest.model.requests.NetworkProvider
import com.vadim.mvptest.presenter.UserInfoPresenter
import com.vadim.mvptest.presenter.UserInfoView
import com.vadim.mvptest.ui.BackButtonListener
import com.vadim.mvptest.utils.loadImageFromUrl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Фрагмент для отображения подробной информации о пользователе
 */
class UserInfoFragment: MvpAppCompatFragment(),UserInfoView, BackButtonListener {

    companion object {
        private const val USER_KEY = "USER_KEY"

        //При создании фрагмента сохраняем объект пользователя в json формате
        fun newInstance(user: GithubUserEntity): UserInfoFragment {
            return UserInfoFragment().apply {
                val jsonString = GsonBuilder().create().toJson(user)
                arguments = Bundle().apply {
                    putString(USER_KEY,jsonString)
                }
            }
        }
    }

    private var adapter: GithubRepositoryItemAdapter? = null

    //Создаём презентёр с cicerone навигацией и восстанавливаем объект пользователя
    private val presenter: UserInfoPresenter by moxyPresenter {
        val arg = arguments?.getString(USER_KEY)
        val user = GsonBuilder().create().fromJson(arg, GithubUserEntity::class.java)
        UserInfoPresenter(
            user,
            GithubRepositoryImpl(
                NetworkProvider.usersApi,
                //GithubAppDB.getInstance(requireContext()),
                App.getUserDB(requireContext()),
                AndroidNetworkStatus(requireContext())),
            App.instance.router)
    }
    
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

    //Действие при нажатии кнопки "Назад"
    override fun backPressed() = presenter.backPressed()

    //Инициализация фрагмента
    override fun init() {
        adapter = GithubRepositoryItemAdapter(presenter.repositoryListPresenter)
        binding.userInfoRecyclerview.adapter = adapter
    }

    //Начало загрузки
    override fun startLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    //Окончание загрузки
    override fun endLoading() {
        binding.progressBar.visibility = View.GONE
    }

    //Обновление списка репозиториев
    override fun showRepositoryInformation() {
        adapter?.notifyDataSetChanged()
    }

    //Показать основную информаицю о пользователе
    override fun showBaseInformation(url: String?, name: String) {
        with(binding){
            if (url != null) userIcon.loadImageFromUrl(url)
            else userIcon.loadImageFromUrl(R.drawable.ic_user_placeholder)

            textViewUserInfoName.text = name
        }
    }

    //Отображение ошибки
    override fun showError(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    //Показать детальную информацию о репозитории
    override fun showDetails(repInfo: GithubRepositoryEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle(repInfo.name)
            .setMessage("Forks: ${repInfo.fork_count} Stars: ${repInfo.stars_count}")
            .setNegativeButton("Закрыть"
            ) { dialog, which -> dialog.dismiss() }
            .show()
    }
}