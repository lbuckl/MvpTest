package com.vadim.mvptest.model

import io.reactivex.rxjava3.core.Observable

/**
 * Интерфейс для функционала RxJava по извлечению пользователей из списка
 */
interface ExecuteUsers {
    fun fromIterable(): Observable<GithubUser>
}