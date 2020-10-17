package com.example.task.models

import com.example.task.helper.caloriesToInt
import com.example.task.helper.fatsToInt
import com.google.gson.annotations.SerializedName

class RecipeResponse : ArrayList<Recipe>()

data class Recipe(
    @SerializedName("calories")
    var calories: String,
    @SerializedName("carbos")
    var carbos: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("difficulty")
    var difficulty: Int,
    @SerializedName("fats")
    var fats: String,
    @SerializedName("headline")
    var headline: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("proteins")
    var proteins: String,
    @SerializedName("thumb")
    var thumb: String,
    @SerializedName("time")
    var time: String
){
    var fatInt : Int = 0
    get() = fats.fatsToInt()

    var caloriesInt : Int = 0
    get() = calories.caloriesToInt()
}