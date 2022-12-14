package com.edushare.weatherapptask

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

import com.edushare.weatherapptask.di.AppModule
import com.edushare.weatherapptask.di.ApplicationComponent
import com.edushare.weatherapptask.di.DaggerApplicationComponent

class MyApplication : Application()  {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}

val Context.appComponent: ApplicationComponent
    get() = when (this) {
        is MyApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }