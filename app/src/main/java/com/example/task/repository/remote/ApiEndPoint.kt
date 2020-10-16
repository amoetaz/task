package com.example.task.repository.remote

import com.example.task.models.Recipe

import retrofit2.Call
import retrofit2.http.GET



interface ApiEndPoint {

    @GET("recipes.json")
    fun getRecipes() : Call<ArrayList<Recipe>>

}