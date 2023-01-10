package com.vadim.mvptest.model.database

interface GithubRepositoryDB {
    fun saveUserToDB(){}

    fun getUserFromDB(id: Int){}
}