package com.edushare.weatherapptask.di

import android.app.Application
import androidx.room.Room
import com.edushare.weatherapptask.room.WeatherQueriesDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule() {

    @Singleton
    @Provides
    fun provideDB(app: Application): WeatherQueriesDb = Room.databaseBuilder(
        app,
        WeatherQueriesDb::class.java,
        "database.db"
    ).build()
}

