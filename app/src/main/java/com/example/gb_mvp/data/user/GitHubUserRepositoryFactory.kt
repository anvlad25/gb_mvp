package com.example.gb_mvp.data.user

import com.example.gb_mvp.data.api.GitHubApiFactory
import com.example.gb_mvp.data.user.datasource.GitHubUserCacheDataSourceImpl
import com.example.gb_mvp.data.user.datasource.GitHubUserDataSourceImpl
import com.example.gb_mvp.storage.GitHubStorageFactory

object GitHubUserRepositoryFactory {
    private val gitHubUserRepository: GitHubUserRepository by lazy(LazyThreadSafetyMode.NONE) {
        GithubUsersRepositoryImpl(
            GitHubUserDataSourceImpl(
                GitHubApiFactory.create()
            ),
            GitHubUserCacheDataSourceImpl(
                GitHubStorageFactory.create()
            ),
            com.example.gb_mvp.data.repository.datasource.GitHubRepositoryDataSourceImpl(
                GitHubApiFactory.create()
            ),
            com.example.gb_mvp.data.repository.datasource.GitHubRepositoryCacheDataSourceImpl(
                GitHubStorageFactory.create()
            )
        )
    }

    fun create(): GitHubUserRepository = gitHubUserRepository
}