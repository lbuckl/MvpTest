package com.vadim.mvptest.model

import io.reactivex.rxjava3.core.Observable

interface ExecuteUsers {
    fun fromIterable(): Observable<GithubUser>
}