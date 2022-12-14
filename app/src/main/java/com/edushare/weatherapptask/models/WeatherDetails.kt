package com.edushare.weatherapptask.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDetails(

    @SerializedName("location")
    val location: Location,

    @SerializedName("current")
    val current: Current
) : Parcelable