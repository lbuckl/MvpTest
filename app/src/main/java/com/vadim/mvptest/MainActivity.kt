package com.vadim.mvptest

import android.os.Bundle
import android.util.Log
import com.vadim.mvptest.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

/**
 * Основное активити:
 * Отправляет задачу презентёру [presenter]
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }
    //private val presenter = MainPresenter(CountersModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.let {
            it.btnCounter1.setOnClickListener { presenter.counterOneClick() }
            it.btnCounter2.setOnClickListener { presenter.counterTwoClick() }
            it.btnCounter3.setOnClickListener { presenter.counterThreeClick() }
        }
    }

    override fun setButtonOneText(text: String) {
        Log.v("@@@","setButtonOneText")
        binding?.btnCounter1?.text = text
    }
    override fun setButtonTwoText(text: String) {
        binding?.btnCounter2?.text = text
    }
    override fun setButtonThreeText(text: String) {
        binding?.btnCounter3?.text = text
    }

}