package com.edushare.weatherapptask.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Current(

    @SerializedName("temp_c")
    val tempCelsius: Double,

    @SerializedName("condition")
    val condition: WeatherCondition,

    @SerializedName("wind_mph")
    val windMph: Double,

    @SerializedName("wind_kph")
    val windKph: Double,

    @SerializedName("wind_degree")
    val windDegree: Double,

    @SerializedName("wind_dir")
    val windDir: String,

    @SerializedName("pressure_mb")
    val pressureMb: Double,

    @SerializedName("pressure_in")
    val pressureIn: Double,

    @SerializedName("precip_mm")
    val precipMm: Double,

    @SerializedName("precip_in")
    val precipIn: Double,

    @SerializedName("humidity")
    val humidity: Double,

    @SerializedName("cloud")
    val cloud: Double,

    @SerializedName("feelslike_c")
    val feelslikeCelsius: Double,

    @SerializedName("vis_km")
    val visKm: Double,

    @SerializedName("vis_miles")
    val visMiles: Double,

    @SerializedName("uv")
    val uv: Double,

    @SerializedName("gust_mph")
    val gustMph: Double,

    @SerializedName("gust_kph")
    val gustKph: Double
) : Parcelable
