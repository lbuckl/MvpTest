package com.vadim.mvptest.model

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.database.GithubRepositoryDB
import com.vadim.mvptest.model.requests.GithubRepositoryRest
import com.vadim.mvptest.model.requests.IDataSource
import com.vadim.mvptest.utils.GithubRepositoryMapper
import com.vadim.mvptest.utils.UserMapper
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: IDataSource,
    private val networkStatus: INetworkStatus
): GithubRepositoryRest, GithubRepositoryDB {

    override fun getUsers(): Single<List<GithubUserEntity>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapUserDtoToEntity)}
    }

    override fun getUserById(login: String): Single<GithubUserEntity> {
        return usersApi.getUser(login)
            .map(UserMapper::mapUserDtoToEntity)
    }

    override fun getCustomInformation(url: String): Single<List<GithubRepositoryEntity>> {
        return usersApi.getGithubRepositoryInfo(url)
            .map { it.map(GithubRepositoryMapper::mapRepositoryDtoToEntity) }
    }

    override fun getUserFromDB(id: Int) {
        super.getUserFromDB(id)
    }

    override fun saveUserToDB() {
        super.saveUserToDB()
    }
}