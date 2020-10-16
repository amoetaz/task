package com.example.task.helper

import com.example.task.repository.remote.BaseCallBack
import com.example.task.repository.remote.BaseResponseListener
import retrofit2.Call

fun <T> Call<T>.start(listener: BaseResponseListener<T>) {
    listener.onLoading()
    this.enqueue(BaseCallBack(listener))
}

//remvoe g and convert it to int for soring
fun String.fatsToInt() : Int{
    try {
        return this.replace(" g" , "").toInt()
    } catch (e: NumberFormatException) {
        return 0
    }
}

//remvoe kcal and convert it to int for soring
fun String.caloriesToInt() : Int{
    try {
        return this.replace(" kcal" , "").toInt()
    } catch (e: NumberFormatException) {
        return 0
    }
}