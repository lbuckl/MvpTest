package com.vadim.mvptest.utils

import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.requests.GithubUserListDTO

object UserMapper {
    fun mapToEntity(dto: GithubUserListDTO): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatar_url,
            repositoryUrl = dto.repos_url
        )
    }
}