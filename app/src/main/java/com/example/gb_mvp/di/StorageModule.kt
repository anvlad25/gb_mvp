package com.example.gb_mvp.di

import android.content.Context
import androidx.room.Room
import com.example.gb_mvp.storage.GitHubStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            .build()
}