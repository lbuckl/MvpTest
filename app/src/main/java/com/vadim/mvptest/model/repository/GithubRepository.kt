package com.vadim.mvptest.model.repository

import com.vadim.mvptest.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(): Single<GithubUser>
}