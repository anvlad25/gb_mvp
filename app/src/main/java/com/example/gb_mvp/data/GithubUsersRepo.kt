package com.example.gb_mvp.data

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class GithubUsersRepo {
    private val repositoriesUsers = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): Single<List<GithubUser>> =
        Single.just(repositoriesUsers)

    fun getUserByLogin(userId: String): Maybe<GithubUser> =
        repositoriesUsers.firstOrNull { user -> user.login == userId }
            ?.let { Maybe.just(it) }
            ?: Maybe.empty()

    fun getLoginByPos(userPos: Int): Single<String> =
        Single.just(repositoriesUsers[userPos].login)
}