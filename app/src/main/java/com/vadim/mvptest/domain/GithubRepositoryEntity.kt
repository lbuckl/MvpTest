package com.vadim.mvptest.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepositoryEntity (
    val name: String,
    val fork_count: Int,
    val stars_count: Int
    ) : Parcelable