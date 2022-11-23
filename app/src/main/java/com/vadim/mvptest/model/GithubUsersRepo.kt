package com.vadim.mvptest.model

/**
 * репозиторий с фиктивными данными, которым будем пользоваться
 * пока нереализуем получение данных из сети:
 */
class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser> {
        return repositories
    }
}
