package com.vadim.mvptest.model.database.githubrepositories

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vadim.mvptest.model.database.users.UserDBObject

@Entity(
    //Конструкция для связки id пользователя с id репозитория
    foreignKeys = [ForeignKey(
        entity = UserDBObject::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "github_repository"
)
data class GithubRepoObject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var userId: String,
    val name: String,
    val fork_count: Int,
    val stars_count: Int
)
