package com.vadim.mvptest.model.requests

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface IDataSource {
    @GET("/users")
    fun getAllUsers(): Single<List<GithubUserDTO>>

    @GET("/users/{login}")
    fun getUser(@Path("login") long: String): Single<GithubUserDTO>
}