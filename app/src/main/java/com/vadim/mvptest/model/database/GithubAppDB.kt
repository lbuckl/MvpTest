package com.vadim.mvptest.model.database

import android.content.Context
import android.util.Log
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

    /*companion object{
        private var instance: GithubAppDB? = null

        fun getInstance(context: Context): GithubAppDB {
            return if (instance == null){
                Log.v("@@@","Create DB")
                Room.databaseBuilder(
                    context,
                    GithubAppDB::class.java,
                    GITHUB_DB_NAME
                ).build()
            } else{
                Log.v("@@@","DB already created")
                instance?: throw RuntimeException(
                    "Database has not been created. Please call create(context)")
            }
        }
    }*/
}