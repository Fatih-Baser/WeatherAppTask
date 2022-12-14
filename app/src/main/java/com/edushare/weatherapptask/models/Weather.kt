package com.edushare.weatherapptask.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val temp: Double,
    val humidity: Double,
    val pressure: Double,
    val icon: String
) : Parcelable