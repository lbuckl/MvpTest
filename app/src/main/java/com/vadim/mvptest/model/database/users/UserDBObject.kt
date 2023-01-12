package com.vadim.mvptest.model.database.users

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных Пользователей Гитхаб для хранения в БД
 */
@Entity(tableName = "users")
data class UserDBObject(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val repositoryUrl: String?
)