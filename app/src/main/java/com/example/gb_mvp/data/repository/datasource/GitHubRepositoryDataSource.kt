package com.example.gb_mvp.data.repository.datasource

import com.example.gb_mvp.data.repository.GitHubRepository
import io.reactivex.rxjava3.core.Single

interface GitHubRepositoryDataSource {

    fun getUserRepositories(login: String): Single<List<GitHubRepository>>

}