package com.example.task.repository.remote

interface BaseResponseListener<T>
{
    fun onSuccess(model: T)
    fun onLoading()
    fun onError(errorMsgId: Int)
}