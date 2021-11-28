package com.example.gb_mvp.data.user

import com.example.gb_mvp.data.repository.GitHubRepository
import io.reactivex.rxjava3.core.Observable

interface GitHubUserRepository {
    fun getUsers(): Observable<List<GitHubUser>>

    fun getUserByLogin(login: String): Observable<GitHubUser>

    fun getUserRepositories(login: String): Observable<List<GitHubRepository>>

}