package com.vadim.mvptest.utils

import com.vadim.mvptest.model.GithubUser
import com.vadim.mvptest.model.requests.GithubUserDTO
import com.vadim.mvptest.model.requests.GithubUserDTOItem

object UserMapper {
    fun mapToEntity(dto: GithubUserDTOItem): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatar_url
        )
    }
}