package com.edushare.weatherapptask.repository

import com.edushare.weatherapptask.models.Weather
import com.edushare.weatherapptask.models.WeatherDetails
import com.edushare.weatherapptask.room.WeatherRoomModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun searchWeatherByQuery(query: String): Single<WeatherDetails>
    fun addWeatherToDB(query: String, weather: Weather)
    fun getWeatherFromDB(query: String): Flowable<List<WeatherRoomModel>>
    fun getPreviousQueries(queryPart: String): Flowable<List<WeatherRoomModel>>
}