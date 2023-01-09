package com.vadim.mvptest.model.requests

import com.vadim.mvptest.model.requests.dto.repository.UserRepositoryDTO
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Интерфейс запросов через retrofit
 */
interface IDataSource {
    //Получить список юзеров
    @GET("/users")
    fun getAllUsers(): Single<List<GithubUserDTO>>
    //Получить данные по конкретному пользователя через ввод логина
    @GET("/users/{login}")
    fun getUser(@Path("login") long: String): Single<GithubUserDTO>
    //Получение детальной информации о репозиториях пользователя
    @GET
    fun getGithubRepositoryInfo(@Url url: String): Single<UserRepositoryDTO>
}