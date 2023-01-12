package com.vadim.mvptest.model.database.githubrepositories

import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO

interface GithubRepoDBImpl {
    fun saveRepositoryToDB(userData: List<GithubUserDTO>)

    fun getRepositoryFromDB(id: Int)
}