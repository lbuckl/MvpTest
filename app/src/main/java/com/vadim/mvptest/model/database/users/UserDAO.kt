package com.vadim.mvptest.model.database.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации запросов в БД Пользователей Гитхаб
 */
@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserDBObject>)

    @Query("SELECT * FROM users")
    fun queryAllUsers(): Single<List<UserDBObject>>
}