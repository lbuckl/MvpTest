package com.vadim.mvptest.model.database.users

import com.vadim.mvptest.model.GithubUserEntity
import com.vadim.mvptest.model.requests.dto.user.GithubUserDTO
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для реализации функций работы с БД Пользователей Гитхаб
 */
interface GithubUserDBImpl {

    fun saveUsersToDB(userData: List<GithubUserDTO>)

    fun getUsersFromDB(): Single<List<GithubUserEntity>>
}