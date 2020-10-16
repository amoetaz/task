package com.example.task.helper


import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Utils {
    companion object {

        inline fun <reified T> convertJsonToObject(json: String): T {
            val gson = Gson()
            return gson.fromJson<T>(json, T::class.java)
        }

        //convert json to array of objects ex:ArrayList<Person> ,hence T = ArrayList<Person>
        inline fun <reified T> convertJsonToArrayOfObjects(json: String): T {
            val listType = object : TypeToken<T>() {}.type
            val gson = Gson()
            return gson.fromJson(json, listType)
        }

        //convert object to json
        fun <T> convertObjectToJson(t: T): String {
            val gson = Gson()
            val json = gson.toJson(t)
            return json
        }

        fun checkInternetConnection(activity: Context): Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            val isConnected = (networkInfo != null && networkInfo.isConnected)
            return isConnected
        }

    }


}