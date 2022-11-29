package com.vadim.mvptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vadim.mvptest.databinding.FragmentUserInfoBinding
import moxy.MvpAppCompatFragment

class UserInfoFragment: MvpAppCompatFragment() {

    companion object {
        fun newInstance() = UserInfoFragment()
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
}