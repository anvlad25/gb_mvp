package com.example.gb_mvp.data.user.datasource

import com.example.gb_mvp.data.user.GitHubUser
import com.example.gb_mvp.storage.GitHubStorage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GitHubUserCacheDataSourceImpl(
    private val gitHubStorage: GitHubStorage
) : GitHubUserCacheDataSource {

    override fun retain(users: List<GitHubUser>): Observable<List<GitHubUser>> =
        gitHubStorage
            .getGitHubUserDao()
            .retain(users)
            .andThen(
                gitHubStorage
                    .getGitHubUserDao()
                    .getUsers()
            )

    override fun retain(user: GitHubUser): Single<GitHubUser> =
        gitHubStorage
            .getGitHubUserDao()
            .retain(user)
            .andThen(
                gitHubStorage
                    .getGitHubUserDao()
                    .getUserByLogin(user.login)
                    .firstOrError()
            )

    override fun getUsers(): Observable<List<GitHubUser>> =
        gitHubStorage
            .getGitHubUserDao()
            .getUsers()

    override fun getUserByLogin(login: String): Observable<GitHubUser> =
        gitHubStorage
            .getGitHubUserDao()
            .getUserByLogin(login)

}