package com.example.gb_mvp.di

import com.example.gb_mvp.data.repository.datasource.GitHubRepositoryCacheDataSource
import com.example.gb_mvp.data.repository.datasource.GitHubRepositoryCacheDataSourceImpl
import com.example.gb_mvp.data.repository.datasource.GitHubRepositoryDataSource
import com.example.gb_mvp.data.repository.datasource.GitHubRepositoryDataSourceImpl
import com.example.gb_mvp.data.user.GitHubUserRepository
import com.example.gb_mvp.data.user.GithubUsersRepositoryImpl
import com.example.gb_mvp.data.user.datasource.GitHubUserCacheDataSource
import com.example.gb_mvp.data.user.datasource.GitHubUserCacheDataSourceImpl
import com.example.gb_mvp.data.user.datasource.GitHubUserDataSource
import com.example.gb_mvp.data.user.datasource.GitHubUserDataSourceImpl
import com.example.gb_mvp.main.MainActivity
import com.example.gb_mvp.user.UserFragment
import com.example.gb_mvp.users.UsersFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [StorageModule::class, NetworkModule::class])
interface UserModule {

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @Binds
    fun bindGitHubUserRepository(
        gitHubUserRepository: GithubUsersRepositoryImpl
    ): GitHubUserRepository

    @Binds
    fun bindGitHubUserDataSource(
        gitHubUserDataSource: GitHubUserDataSourceImpl
    ): GitHubUserDataSource

    @Binds
    fun bindGitHubUserCacheDataSource(
        gitHubUserCacheDataSource: GitHubUserCacheDataSourceImpl
    ): GitHubUserCacheDataSource

    @Binds
    fun bindGitHubRepositoryDataSource(
        gitHubRepositoryDataSource: GitHubRepositoryDataSourceImpl
    ): GitHubRepositoryDataSource

    @Binds
    fun bindGitHubRepositoryCacheDataSource(
        gitHubRepositoryCacheDataSource: GitHubRepositoryCacheDataSourceImpl
    ): GitHubRepositoryCacheDataSource
}