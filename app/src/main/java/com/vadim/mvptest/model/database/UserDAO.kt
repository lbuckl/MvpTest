package com.vadim.mvptest.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserDBObject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserDBObject>)

    @Query("SELECT * FROM users")
    fun queryAllUsers(): Single<List<UserDBObject>>


}