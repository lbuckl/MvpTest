package com.vadim.mvptest.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadim.mvptest.model.database.githubrepositories.GithubRepoDAO
import com.vadim.mvptest.model.database.githubrepositories.GithubRepoObject
import com.vadim.mvptest.model.database.users.UserDAO
import com.vadim.mvptest.model.database.users.UserDBObject

@Database(
    entities = [UserDBObject::class, GithubRepoObject::class],
    version = 3
)
abstract class GithubAppDB: RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repoDao: GithubRepoDAO
}