package com.vadim.mvptest.model.requests

import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTO
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface IDataSource {
    @GET("/users")
    fun getAllUsers(): Single<List<GithubUserDTO>>

    @GET("/users/{login}")
    fun getUser(@Path("login") long: String): Single<GithubUserDTO>

    @GET
    fun getCustomInformation(@Url url: String): Single<UserRepositoryDTO>
}