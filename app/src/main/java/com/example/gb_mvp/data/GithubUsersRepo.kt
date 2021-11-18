package com.example.gb_mvp.data

class GithubUsersRepo {
    private val repositoriesUsers = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): List<GithubUser> {
        return repositoriesUsers
    }

    fun getUserByLogin(userId: String): GithubUser? =
        repositoriesUsers.firstOrNull { user -> user.login == userId }

    fun getLoginByPos(userPos: Int): String =
        repositoriesUsers[userPos].login
}