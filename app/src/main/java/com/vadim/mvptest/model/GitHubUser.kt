package com.vadim.mvptest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Класс хранения данных пользователя гитхаб
 */
@Parcelize
data class GithubUser(
    val login: String
) : Parcelable

