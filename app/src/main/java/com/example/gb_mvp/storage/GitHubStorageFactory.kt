package com.example.gb_mvp.storage

import androidx.room.Room
import com.example.gb_mvp.App.ContextHolder.context

object GitHubStorageFactory {
    private val inMemoryDatabase: GitHubStorage by lazy {
        Room.inMemoryDatabaseBuilder(context, GitHubStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

    private val database: GitHubStorage by lazy {
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            //.addMigrations(GitHub1to2Migration)
            //.addMigrations(GitHub2to3Migration)
            .build()
    }

    fun create(): GitHubStorage = database
}