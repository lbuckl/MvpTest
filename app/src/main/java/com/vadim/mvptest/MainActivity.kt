package com.vadim.mvptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.vadim.mvptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
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
                0 -> it.btnCounter1.text = text
                1 -> it.btnCounter2.text = text
                2 -> it.btnCounter3.text = text
            }
        }
    }
}