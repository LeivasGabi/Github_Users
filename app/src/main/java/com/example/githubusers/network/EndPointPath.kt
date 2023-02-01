package com.example.githubusers.network

import com.example.githubusers.model.Repository
import com.example.githubusers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPointPath {
    @GET("/users/{userName}")
    fun getUsers(
        @Path("userName")
        userName: String
    ) : Call<User>

    @GET("/users/{userName}/repos")
    fun getRepos(
        @Path("userName")
        userName: String
    ) : Call<List<Repository>>
}