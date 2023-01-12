package com.vadim.mvptest.model.database.githubrepositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface GithubRepoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepo(repos: List<GithubRepoObject>)

    @Query("SELECT * FROM github_repository WHERE userId = :userId")
    fun queryAllRepos(userId: Int): Single<List<GithubRepoObject>>
}