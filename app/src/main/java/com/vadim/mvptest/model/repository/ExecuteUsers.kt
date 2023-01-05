package com.vadim.mvptest.model.repository

import com.vadim.mvptest.model.GithubUser
import io.reactivex.rxjava3.core.Observable

/**
 * Интерфейс для функционала RxJava по извлечению пользователей из списка
 */
interface ExecuteUsers {
    fun fromIterable(): Observable<GithubUser>
}