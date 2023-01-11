package com.vadim.mvptest.model.database.users

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserDBObject::class],
    version = 1
)
abstract class GithubAppDB: RoomDatabase() {
    abstract val userDao: UserDAO
}