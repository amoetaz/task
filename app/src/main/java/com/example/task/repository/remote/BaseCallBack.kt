package com.example.task.repository.remote

import android.util.Log
import com.example.task.R
import retrofit2.*
import java.net.SocketException


class BaseCallBack<T>(val listener: BaseResponseListener<T>) : Callback<T>
{
    override fun onResponse(call: Call<T>, response: Response<T>)
    {

        when (response.code() )
        {
            200 ->
            {
                response.body().let {
                    listener.onSuccess(it!!)
                }
            }
            401 -> listener.onError(R.string.unauthorized_user)
            500 -> listener.onError(R.string.server_error)
            else -> {
                listener.onError(R.string.unexpected_error)
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable)
    {

        Log.d("sdscsdcds" , t.message.toString())

        listener.onError(t.handleError())
    }

}

private fun Throwable.handleError(): Int = when (this)
{

    is HttpException ->
    {
        when (code())
        {
            400 -> R.string.bad_request
            401 -> R.string.unauthorized_user
            404 -> R.string.request_not_found
            500 -> R.string.server_error
            else -> R.string.unexpected_error
        }
    }
    is SocketException -> R.string.not_connected_to_network

    else -> {
        R.string.unexpected_error
    }
}
