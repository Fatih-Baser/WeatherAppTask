package com.edushare.weatherapptask

import com.edushare.weatherapptask.models.WeatherDetails
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    fun getSearchingWeather(
        @Query("key") key: String,
        @Query("q") q: String
    ): Single<WeatherDetails>
}