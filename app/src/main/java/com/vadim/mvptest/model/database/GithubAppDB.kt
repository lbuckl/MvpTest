package com.vadim.mvptest.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vadim.mvptest.GITHUB_DB_NAME

@Database(
    entities = [UserDBObject::class],
    version = 1
)
abstract class GithubAppDB: RoomDatabase() {

    fun create(context: Context): GithubAppDB{
        return Room.databaseBuilder(
            context,
            GithubAppDB::class.java,
            GITHUB_DB_NAME
        ).build()
    }
}