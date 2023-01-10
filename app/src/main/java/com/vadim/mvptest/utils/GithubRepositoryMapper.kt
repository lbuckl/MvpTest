package com.vadim.mvptest.utils

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTOItem

object GithubRepositoryMapper {

    fun mapRepositoryDtoToEntity(dto: UserRepositoryDTOItem): GithubRepositoryEntity {
        return GithubRepositoryEntity(
            name = dto.name,
            fork_count = dto.forks_count,
            stars_count = dto.stargazers_count
        )
    }
}