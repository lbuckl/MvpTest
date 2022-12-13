package com.vadim.mvptest.model

import io.reactivex.rxjava3.core.Observable

/**
 * Репозиторий с фиктивными данными, которым будем пользоваться
 * пока нереализуем получение данных из сети:
 */
class GithubUsersRepo: ExecuteUsers {

    private val userList = mutableListOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
    )

    //функция запускает поток данных
    override fun fromIterable():Observable<GithubUser>{
        return Observable.fromIterable(
            userList
        )
    }
}
