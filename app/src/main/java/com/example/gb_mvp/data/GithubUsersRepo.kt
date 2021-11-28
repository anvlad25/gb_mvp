package com.example.gb_mvp.data

import com.example.gb_mvp.data.api.GitHubApi
import com.example.gb_mvp.data.api.GitHubApiFactory
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class GithubUsersRepo(private val repositoriesUsers: GitHubApi = GitHubApiFactory.create()) {

    fun getUsers(): Single<List<GithubUser>> =
        repositoriesUsers.fetchUsers()

    fun getUserByLogin(login: String): Maybe<GithubUser> =
        repositoriesUsers
            .fetchUserByLogin(login)
            .onErrorComplete()

    fun getUserRepo(login: String): Maybe<List<UserRepos>> =
        repositoriesUsers
            .fetchUserRepositories(login)
            .onErrorComplete()
}