package com.vadim.mvptest.model.database.githubrepo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface GithubRepoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepo(repos: List<GithubRepoObject>)

    @Query("SELECT * FROM repository")
    fun queryAllRepos(): Single<List<GithubRepoObject>>
}