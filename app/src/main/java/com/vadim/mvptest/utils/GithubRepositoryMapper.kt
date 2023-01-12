package com.vadim.mvptest.utils

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.database.githubrepositories.GithubRepoObject
import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTOItem

object GithubRepositoryMapper {

    fun mapRepositoryDtoToEntity(dto: UserRepositoryDTOItem): GithubRepositoryEntity {
        return GithubRepositoryEntity(
            name = dto.name,
            fork_count = dto.forks_count,
            stars_count = dto.stargazers_count
        )
    }

    //Преобразовывает формат данных из запроса в формат для БД
    fun mapRepoDtoToDb(userIdReq:Int, dto: UserRepositoryDTOItem): GithubRepoObject {
        return GithubRepoObject(
            id = dto.id,
            userId = userIdReq,
            name = dto.name,
            fork_count = dto.forks_count,
            stars_count = dto.stargazers_count
        )
    }

    fun mapRepoDbToEntity(githubRepoObject: GithubRepoObject): GithubRepositoryEntity {
        return GithubRepositoryEntity(
            name = githubRepoObject.name,
            fork_count = githubRepoObject.fork_count,
            stars_count = githubRepoObject.stars_count
        )
    }
}