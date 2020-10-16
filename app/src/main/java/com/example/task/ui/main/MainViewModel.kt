package com.rehabtech.rtech.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.models.Recipe
import com.example.task.models.RecipeResponse
import com.example.task.repository.remote.BaseResponseListener
import com.example.task.ui.main.MainRepository


class MainViewModel : ViewModel() {

    internal val recipeResponse by lazy { MutableLiveData<ArrayList<Recipe>>() }
    internal val isLoading by lazy { MutableLiveData<Boolean>() }
    internal val errorInt by lazy { MutableLiveData<Int>() }


    fun getRecipes(){
        MainRepository.getSliderItems(object : BaseResponseListener<ArrayList<Recipe>> {
            override fun onSuccess(model: ArrayList<Recipe>) {
                isLoading.value = false
                recipeResponse.value = model
            }

            override fun onLoading() {
                isLoading.value = true
            }

            override fun onError(errorMsgId: Int) {
                errorInt.value = errorMsgId
                isLoading.value = false
            }
        })
    }


}