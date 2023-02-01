package com.example.githubusers.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository(
    @SerializedName("name")
    var name: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("html_url")
    var htmlUrl: String
): Serializable
