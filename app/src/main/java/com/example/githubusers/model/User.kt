package com.example.githubusers.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("repos_url")
    var reposUrl: String,
    @SerializedName("name")
    var name: String?,
    @SerializedName("public_repo")
    var publicRepos: Int
): Serializable
