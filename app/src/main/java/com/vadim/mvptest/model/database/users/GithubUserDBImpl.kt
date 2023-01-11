package com.vadim.mvptest.model.database.users

import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO

interface GithubUserDBImpl {
    fun saveUserToDB(userData: List<GithubUserDTO>)

    fun getUserFromDB(id: Int)
}