package com.vadim.mvptest.model.repository

interface GithubRepositoryDB {
    fun saveUserToDB(){}

    fun getUserFromDB(id: Int){}
}