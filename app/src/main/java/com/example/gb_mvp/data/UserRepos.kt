package com.example.gb_mvp.data

import com.google.gson.annotations.SerializedName

class UserRepos(
    @SerializedName("name")
    val name: String,

    @SerializedName("forks")
    val forks: String,

    @SerializedName("watchers")
    val watchers: String
)