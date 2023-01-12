package com.vadim.mvptest.model.database.githubrepositories

import com.vadim.mvptest.domain.GithubRepositoryEntity
import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTO
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import io.reactivex.rxjava3.core.Single

interface GithubRepoDBImpl {
    fun saveRepositoryToDB(userId:Int, repoData: UserRepositoryDTO)

    fun getRepositoryFromDB(userId: Int): Single<List<GithubRepositoryEntity>>
}