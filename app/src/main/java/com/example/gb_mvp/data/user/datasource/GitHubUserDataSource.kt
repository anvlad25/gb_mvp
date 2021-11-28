package com.example.gb_mvp.data.user.datasource

import com.example.gb_mvp.data.user.GitHubUser
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface GitHubUserDataSource {

    fun getUsers(): Single<List<GitHubUser>>

    fun getUserByLogin(login: String): Maybe<GitHubUser>

}