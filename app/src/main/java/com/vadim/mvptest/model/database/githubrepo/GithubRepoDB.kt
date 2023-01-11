package com.vadim.mvptest.model.database.githubrepo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadim.mvptest.model.database.UserDAO

@Database(
    entities = [GithubRepoObject::class],
    version = 1
)
abstract class GithubRepoDB: RoomDatabase() {
    abstract val repoDao: GithubRepoDAO
}