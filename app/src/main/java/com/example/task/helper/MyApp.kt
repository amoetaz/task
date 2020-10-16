package com.example.task.helper

import android.app.Application
import com.example.task.repository.local.SessionManager

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SessionManager(this)
    }
}