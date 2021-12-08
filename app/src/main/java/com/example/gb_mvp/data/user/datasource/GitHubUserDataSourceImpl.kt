package com.example.gb_mvp.data.user.datasource

import com.example.gb_mvp.data.user.GitHubUser
import com.example.gb_mvp.data.api.GitHubApi
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GitHubUserDataSourceImpl
@Inject constructor(
    private val gitHubApi: GitHubApi
) : GitHubUserDataSource {

    override fun getUsers(): Single<List<GitHubUser>> =
        gitHubApi
            .fetchUsers()
            .delay(2L, TimeUnit.SECONDS)

    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        gitHubApi
            .fetchUserByLogin(login)
            .onErrorComplete()

}