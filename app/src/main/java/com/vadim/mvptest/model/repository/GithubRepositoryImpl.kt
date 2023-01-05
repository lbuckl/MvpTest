package com.vadim.mvptest.model.repository

import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.requests.IDataSource
import com.vadim.mvptest.utils.UserMapper
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: IDataSource
): GithubRepository {
    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity)}
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }
}