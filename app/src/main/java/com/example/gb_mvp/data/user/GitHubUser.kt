package com.example.gb_mvp.data.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "github_users")
data class GitHubUser(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo
    @SerializedName("login")
    val login: String,

    @ColumnInfo
    @SerializedName("name")
    val name: String?,

    @ColumnInfo
    @SerializedName("avatar_url")
    val avatar: String,

    @ColumnInfo
    @SerializedName("repos_url")
    val repos_url: String
) : Parcelable
