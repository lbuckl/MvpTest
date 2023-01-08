package com.vadim.mvptest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Класс хранения данных пользователя гитхаб
 */
@Parcelize
data class GithubUserEntity(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val repositoryUrl: String?
) : Parcelable

