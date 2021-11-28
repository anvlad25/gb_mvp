package com.example.gb_mvp.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gb_mvp.data.repository.GitHubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface GitHubRepositoryDao {

    @Query("SELECT * FROM github_repositories WHERE login LIKE :login")
    fun getRepositoriesByLogin(login: String): Observable<List<GitHubRepository>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<GitHubRepository>): Completable

}