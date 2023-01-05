package com.vadim.mvptest.model.repository


import com.vadim.mvptest.model.GithubUser
import io.reactivex.rxjava3.core.Observable

/**
 * Репозиторий с фиктивными данными, которым будем пользоваться
 * пока нереализуем получение данных из сети:
 */
class GithubUsersRepo: ExecuteUsers {

    private val userList = mutableListOf(
            GithubUser(1,"login1",""),
            GithubUser(2,"login2",""),
            GithubUser(3,"login3",""),
            GithubUser(4,"login4",""),
            GithubUser(5,"login5","")
    )

    //функция запускает поток данных
    override fun fromIterable():Observable<GithubUser>{
        return Observable.fromIterable(
            userList
        )
    }
}
