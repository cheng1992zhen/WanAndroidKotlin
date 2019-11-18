package com.example.wanandroidkotlin.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import kotlin.properties.Delegates

class App : MultiDexApplication() {
    companion object {
        private val TAG = "App"

        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application


        // 用户信息
      //  var userInfo: UserInfoBody? = null

    }

    override fun onCreate() {
        super.onCreate()
    }
}
