package com.edushare.weatherapptask.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherRoomModel::class], version = 1)
abstract class WeatherQueriesDb : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}