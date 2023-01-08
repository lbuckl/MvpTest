package com.vadim.mvptest.utils

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTOItem

object Mapper {
    fun mapUserDtoToEntity(dto: GithubUserDTO): GithubUserEntity {
        return GithubUserEntity(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatar_url,
            repositoryUrl = dto.repos_url
        )
    }

    fun mapRepositoryDtoToEntity(dto: UserRepositoryDTOItem): GithubRepositoryEntity{
        return GithubRepositoryEntity(
            name = dto.name,
            fork_count = dto.forks_count,
            stars_count = dto.stargazers_count
        )
    }
}