package com.example.gb_mvp.data.repository.datasource

import com.example.gb_mvp.data.api.GitHubApi
import com.example.gb_mvp.data.repository.GitHubRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubRepositoryDataSourceImpl
@Inject constructor(
    private val gitHubApi: GitHubApi
): GitHubRepositoryDataSource {

    override fun getUserRepositories(login: String): Single<List<GitHubRepository>> =
        gitHubApi
            .fetchUserRepositories(login)

}