package com.vadim.mvptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.vadim.mvptest.databinding.ActivityMainBinding

/**
 * Основное активити:
 * Отправляет задачу презентёру [presenter]
 * Принимает коллбэк от презентёра [setButtonText]
 */
class MainActivity : AppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val listener = View.OnClickListener {
            presenter.counterClick(getNumericViewId(it))
        }

        binding?.let {
            it.btnCounter1.setOnClickListener(listener)
            it.btnCounter2.setOnClickListener(listener)
            it.btnCounter3.setOnClickListener(listener)
        }
    }

    override fun setButtonText(index: Int, text: String) {
        binding?.let{
            when(index){
                BUTTON_ONE -> it.btnCounter1.text = text
                BUTTON_TWO -> it.btnCounter2.text = text
                BUTTON_THREE -> it.btnCounter3.text = text
            }
        }
    }


}