package com.edushare.weatherapptask.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.edushare.weatherapptask.ApiService

import com.edushare.weatherapptask.fragments.SearchFragment
import com.edushare.weatherapptask.repository.WeatherRepository
import com.edushare.weatherapptask.repository.WeatherRepositoryImpl
import com.edushare.weatherapptask.room.WeatherQueriesDb
import dagger.Binds


import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun context(): Context
    fun applicationContext(): Application

    fun inject(fragment: SearchFragment)
}

@Module(
    includes = [
        NetworkModule::class,
        DataBaseModule::class,
        AppBindModule::class
    ]
)
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application
}

