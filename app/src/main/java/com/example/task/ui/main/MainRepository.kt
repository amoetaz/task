package com.example.task.ui.main

import com.example.task.helper.start
import com.example.task.models.Recipe
import com.example.task.models.RecipeResponse
import com.example.task.repository.remote.ApiConfigure
import com.example.task.repository.remote.ApiEndPoint
import com.example.task.repository.remote.BaseResponseListener
import retrofit2.Call

object MainRepository {

    fun getSliderItems(callback : BaseResponseListener<ArrayList<Recipe>>){
        val service = ApiConfigure.mainRetrofit.create(ApiEndPoint::class.java)

        val call : Call<ArrayList<Recipe>> = service.getRecipes()
        call.start(callback)

    }



}