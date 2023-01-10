package com.vadim.mvptest.utils

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.database.UserDBObject
import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTOItem
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
    fun mapUserDtoToDbEntity(dto: GithubUserDTO): UserDBObject {
        return UserDBObject(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatar_url,
            repositoryUrl = dto.repos_url
        )
    }
}