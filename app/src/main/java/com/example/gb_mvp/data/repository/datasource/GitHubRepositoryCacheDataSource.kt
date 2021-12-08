package com.example.gb_mvp.data.repository.datasource

import com.example.gb_mvp.data.repository.GitHubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface GitHubRepositoryCacheDataSource {

    fun getUserRepositories(login: String): Observable<List<GitHubRepository>>
    fun retain(repositories: List<GitHubRepository>): Completable

}