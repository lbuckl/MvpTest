package com.vadim.mvptest.model.repository

import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.requests.NetworkProvider
import com.vadim.mvptest.utils.UserMapper
import io.reactivex.rxjava3.core.Single

object GithubRepositoryImpl: GithubRepository {
    val usersApi = NetworkProvider.usersApi



    var lastUserLogin = ""

    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity)}
    }

    override fun getUserById(): Single<GithubUser> {
        return usersApi.getUser(lastUserLogin)
            .map(UserMapper::mapToEntity)
    }
}