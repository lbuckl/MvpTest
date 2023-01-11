package com.vadim.mvptest.model.database

import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import io.reactivex.rxjava3.core.Completable

interface GithubRepositoryDB {
    fun saveUserToDB(userData: List<GithubUserDTO>)

    fun getUserFromDB(id: Int)
}