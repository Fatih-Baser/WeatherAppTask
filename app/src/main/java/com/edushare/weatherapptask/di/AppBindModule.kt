package com.edushare.weatherapptask.di

import com.edushare.weatherapptask.repository.WeatherRepository
import com.edushare.weatherapptask.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface AppBindModule {

    @Binds
    fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}

