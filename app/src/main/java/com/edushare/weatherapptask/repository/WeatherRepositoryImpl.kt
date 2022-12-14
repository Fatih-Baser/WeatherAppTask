package com.edushare.weatherapptask.repository

import com.edushare.weatherapptask.ApiService
import com.edushare.weatherapptask.models.Weather
import com.edushare.weatherapptask.models.WeatherDetails
import com.edushare.weatherapptask.room.WeatherQueriesDb
import com.edushare.weatherapptask.room.WeatherRoomModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val db: WeatherQueriesDb
) : WeatherRepository {

    override fun searchWeatherByQuery(query: String): Single<WeatherDetails> {
        val key = "7de8f53141af446ca3c160606221212"
        return api.getSearchingWeather(key, query)
    }

    override fun getPreviousQueries(queryPart: String): Flowable<List<WeatherRoomModel>> {
        return db.weatherDao().selectByPartOfQuery(queryPart)
    }

    override fun addWeatherToDB(query: String, weather: Weather) {
        Single.fromCallable {
            val entity = WeatherRoomModel(
                id = query.hashCode(),
                query = query,
                place = weather.place,
                latitude = weather.latitude,
                longitude = weather.longitude,
                temp = weather.temp,
                humidity = weather.humidity,
                pressure = weather.pressure,
                icon = weather.icon
            )
            db.weatherDao().insertWeather(
                entity
            )
            entity
        }
            .subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getWeatherFromDB(query: String): Flowable<List<WeatherRoomModel>> {
        return db.weatherDao().selectByWeatherByQuery(query)
    }
}
