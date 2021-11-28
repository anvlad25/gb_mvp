package com.example.gb_mvp.data.repository.datasource

import com.example.gb_mvp.data.repository.GitHubRepository
import com.example.gb_mvp.storage.GitHubStorage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class GitHubRepositoryCacheDataSourceImpl(
    private val gitHubStorage: GitHubStorage
): GitHubRepositoryCacheDataSource {

    override fun getUserRepositories(login: String): Observable<List<GitHubRepository>> =
        gitHubStorage
            .getGitHubRepositoryDao()
            .getRepositoriesByLogin(login)

    override fun retain(repositories: List<GitHubRepository>): Completable =
        gitHubStorage
            .getGitHubRepositoryDao()
            .retain(repositories)

}