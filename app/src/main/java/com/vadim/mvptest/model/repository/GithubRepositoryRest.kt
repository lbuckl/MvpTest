package com.vadim.mvptest.model.repository

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.GithubUserEntity
import io.reactivex.rxjava3.core.Single

interface GithubRepositoryRest {

    fun getUsers(): Single<List<GithubUserEntity>>

    fun getUserById(login: String): Single<GithubUserEntity>

    fun getCustomInformation(url: String): Single<List<GithubRepositoryEntity>>
}