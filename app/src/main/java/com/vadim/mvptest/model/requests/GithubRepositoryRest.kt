package com.vadim.mvptest.model.requests

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.GithubUserEntity
import io.reactivex.rxjava3.core.Single

interface GithubRepositoryRest {

    fun getUsers(): Single<List<GithubUserEntity>>

    fun getRepositoryInformation(userId: Int, url: String): Single<List<GithubRepositoryEntity>>
}