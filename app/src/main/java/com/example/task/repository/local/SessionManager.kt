package com.example.task.repository.local

import android.content.Context
import android.content.SharedPreferences


class SessionManager(context: Context) {

    init {
        pref = context.getSharedPreferences("filePrefs" , Context.MODE_PRIVATE)
    }

    companion object{

        private lateinit var pref: SharedPreferences

        private val DATA = "DATA"
        private val SELECTED_SORT = "SELECTED_SORT"

        fun getData() = pref.getString(DATA, null)

        fun setData(data: String) = pref.edit()
            .putString(DATA, data)
            .apply()

        fun getSelectedSort() = pref.getString(SELECTED_SORT, "none")

        fun setSelectedSort(data: String) = pref.edit()
            .putString(SELECTED_SORT, data)
            .apply()

    }


}