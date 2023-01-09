package com.vadim.mvptest.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDBObject: UserDBObject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userDBObject: UserDBObject)

    @Query("SELECT * FROM users")
    fun queryAllUsers(): List<UserDBObject>
}