package com.vadim.mvptest.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vadim.mvptest.R

fun ImageView.loadImageFromUrl(url: String){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_user_placeholder)
        .into(this)
}