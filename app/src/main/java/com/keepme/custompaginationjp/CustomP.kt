package com.keepme.custompaginationjp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CustomP : Application(){

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    /** -- Provide Context for access anywhere of codebase -- **/
    companion object {
        lateinit var appContext: Context
    }

}