package com.vadim.mvptest.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vadim.mvptest.R

/**
 * Функция загрузки изображния по URL с помощью Glide
 */
fun ImageView.loadImageFromUrl(url: String){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_user_placeholder)
        .into(this)
}

/**
 * Функция загрузки изображния по URL с помощью Glide
 */
fun ImageView.loadImageFromUrl(url: Int){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_user_placeholder)
        .into(this)
}