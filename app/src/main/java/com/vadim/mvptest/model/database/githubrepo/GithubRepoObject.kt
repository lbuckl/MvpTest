package com.vadim.mvptest.model.database.githubrepo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class GithubRepoObject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val fork_count: Int,
    val stars_count: Int
)
