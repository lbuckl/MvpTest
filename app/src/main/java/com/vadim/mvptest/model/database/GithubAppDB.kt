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
    abstract val userDao: UserDAO

    companion object{
        private const val DB_NAME ="database.db"

        private var instance: GithubAppDB? = null

        fun getInstance(context: Context): GithubAppDB {
            return if (instance == null){
                Room.databaseBuilder(
                    context,
                    GithubAppDB::class.java,
                    GITHUB_DB_NAME
                ).build()
            } else instance?: throw RuntimeException(
                "Database has not been created. Please call create(context)")
        }
    }
}