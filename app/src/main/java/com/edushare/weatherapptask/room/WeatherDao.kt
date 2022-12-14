package com.edushare.weatherapptask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import io.reactivex.rxjava3.core.Flowable

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather WHERE `query` = :q LIMIT 1")
    fun selectByWeatherByQuery(q: String): Flowable<List<WeatherRoomModel>>

    @Query("SELECT * FROM weather WHERE `query` LIKE '%' || :q || '%'")
    fun selectByPartOfQuery(q: String): Flowable<List<WeatherRoomModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherRoomModel)
}