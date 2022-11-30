package com.vadim.mvptest.model

import io.reactivex.rxjava3.core.Observable

/**
 * Репозиторий с фиктивными данными, которым будем пользоваться
 * пока нереализуем получение данных из сети:
 */
class GithubUsersRepo: ExecuteUsers {
    override fun fromIterable():Observable<GithubUser>{
        return Observable.fromIterable(
            listOf(
                GithubUser("login1"),
                GithubUser("login2"),
                GithubUser("login3"),
                GithubUser("login4"),
                GithubUser("login5")
            )
        )
    }
}
