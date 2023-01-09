package com.vadim.mvptest.model.repository

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.requests.IDataSource
import com.vadim.mvptest.utils.Mapper
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: IDataSource
): GithubRepository {

    override fun getUsers(): Single<List<GithubUserEntity>> {
        return usersApi.getAllUsers()
            .map { it.map(Mapper::mapUserDtoToEntity)}
    }

    override fun getUserById(login: String): Single<GithubUserEntity> {
        return usersApi.getUser(login)
            .map(Mapper::mapUserDtoToEntity)
    }

    override fun getCustomInformation(url: String): Single<List<GithubRepositoryEntity>> {
        return usersApi.getGithubRepositoryInfo(url)
            .map { it.map(Mapper::mapRepositoryDtoToEntity) }
    }
}