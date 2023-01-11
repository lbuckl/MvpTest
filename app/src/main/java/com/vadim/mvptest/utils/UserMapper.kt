package com.vadim.mvptest.utils

import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.database.users.UserDBObject
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO

object UserMapper {

    //Преобразовывает формат данных из запроса в доменный формат
    fun mapUserDtoToEntity(dto: GithubUserDTO): GithubUserEntity {
        return GithubUserEntity(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatar_url,
            repositoryUrl = dto.repos_url
        )
    }

    //Преобразовывает формат данных из запроса в формат для БД
    fun mapUserDtoToDb(dto: GithubUserDTO): UserDBObject {
        return UserDBObject(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatar_url,
            repositoryUrl = dto.repos_url
        )
    }

    fun mapUserDbToEntity(userDBObject: UserDBObject): GithubUserEntity{
        return GithubUserEntity(
            id = userDBObject.id,
            login = userDBObject.login,
            avatarUrl = userDBObject.avatarUrl,
            repositoryUrl = userDBObject.repositoryUrl
        )
    }
}