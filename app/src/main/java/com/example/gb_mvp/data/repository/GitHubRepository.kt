package com.example.gb_mvp.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_repositories")
data class GitHubRepository(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    val login: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("forks")
    val forks: String,

    @SerializedName("watchers")
    val watchers: String
)