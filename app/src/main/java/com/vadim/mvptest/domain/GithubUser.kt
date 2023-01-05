package com.vadim.mvptest.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Класс хранения данных пользователя гитхаб
 */
@Parcelize
data class GithubUser(
    val login: String,
    val avatarUrl: String
) : Parcelable

