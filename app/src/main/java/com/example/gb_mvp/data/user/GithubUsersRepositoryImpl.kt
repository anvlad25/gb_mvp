package com.example.gb_mvp.data.user

import com.example.gb_mvp.data.repository.GitHubRepository
import com.example.gb_mvp.data.user.datasource.GitHubUserCacheDataSource
import com.example.gb_mvp.data.user.datasource.GitHubUserDataSource
import com.example.gb_mvp.data.repository.datasource.GitHubRepositoryCacheDataSource
import com.example.gb_mvp.data.repository.datasource.GitHubRepositoryDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GithubUsersRepositoryImpl
@Inject constructor(
    private val gitHubUserDataSource: GitHubUserDataSource,
    private val gitHubUserCacheDataSource: GitHubUserCacheDataSource,
    private val gitHubRepositoryDataSource: GitHubRepositoryDataSource,
    private val gitHubRepositoryCacheDataSource: GitHubRepositoryCacheDataSource,
) : GitHubUserRepository {

    override fun getUsers(): Observable<List<GitHubUser>> =
        Observable.merge(
            gitHubUserCacheDataSource
                .getUsers(),
            gitHubUserDataSource
                .getUsers()
                .flatMapObservable(gitHubUserCacheDataSource::retain)
        )

    override fun getUserByLogin(login: String): Observable<GitHubUser> =
        Observable.merge(
            gitHubUserCacheDataSource
                .getUserByLogin(login),
            gitHubUserDataSource
                .getUserByLogin(login)
                .flatMapCompletable { user ->
                    gitHubUserCacheDataSource
                        .retain(user)
                        .flatMapCompletable {
                            gitHubRepositoryDataSource
                                .getUserRepositories(user.login)
                                .map { repositories ->
                                    repositories.map { repository ->
                                        repository.copy(
                                            login = user.login
                                        )
                                    }
                                }
                                .flatMapCompletable(gitHubRepositoryCacheDataSource::retain)
                        }
                }
                .toObservable()
        )

    override fun getUserRepositories(login: String): Observable<List<GitHubRepository>> =
        Observable.merge(
            gitHubRepositoryCacheDataSource
                .getUserRepositories(login),
            gitHubRepositoryDataSource
                .getUserRepositories(login)
                .map { repositories -> repositories.map { repository -> repository.copy(login = login) } }
                .flatMapCompletable(gitHubRepositoryCacheDataSource::retain)
                .toObservable()
        )
}