package com.edushare.weatherapptask.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherRoomModel(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "query")
    val query: String,

    @ColumnInfo(name = "place")
    val place: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "temp")
    val temp: Double,

    @ColumnInfo(name = "humidity")
    val humidity: Double,

    @ColumnInfo(name = "pressure")
    val pressure: Double,

    @ColumnInfo(name = "icon")
    val icon: String
)