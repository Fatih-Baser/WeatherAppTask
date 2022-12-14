package com.edushare.weatherapptask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edushare.weatherapptask.repository.WeatherRepository
import com.edushare.weatherapptask.models.State
import com.edushare.weatherapptask.models.Weather
import com.edushare.weatherapptask.room.WeatherRoomModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val weatherMutableLiveData = MutableLiveData<State<Weather>>()
    val weatherLiveData: LiveData<State<Weather>> get() = weatherMutableLiveData

    fun searchWeather(q: String, connection: Boolean) {
        Log.d("INTERNET", "INTERNET $connection")
        weatherMutableLiveData.postValue(State.Loading)
        if (connection) {
            weatherRepository.searchWeatherByQuery(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val weather = Weather(
                        place = "${it.location.country}, ${it.location.name}",
                        latitude = it.location.lat,
                        longitude = it.location.lon,
                        temp = it.current.tempCelsius,
                        humidity = it.current.humidity,
                        pressure = it.current.pressureIn,
                        icon = it.current.condition.icon
                    )
                    weatherMutableLiveData.postValue(
                        State.Success(
                            weather
                        )
                    )

                    weatherRepository.addWeatherToDB(q, weather)
                }, {
                    weatherMutableLiveData.postValue(State.Error(it.message.toString()))
                })
        } else {
            weatherRepository.getWeatherFromDB(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    val result = list.last()
                    val weather = Weather(
                        place = result.place,
                        latitude = result.latitude,
                        longitude = result.longitude,
                        temp = result.temp,
                        humidity = result.humidity,
                        pressure = result.pressure,
                        icon = result.icon
                    )
                    weatherMutableLiveData.postValue(
                        State.Success(
                            weather
                        )
                    )
                }, {
                    weatherMutableLiveData.postValue(State.Error(it.message.toString()))
                })

        }
    }

    private val previousQueriesMutableLiveData = MutableLiveData<List<String>>()
    val previousQueriesLiveData: LiveData<List<String>> get() = previousQueriesMutableLiveData

    fun checkQuery(q: String) {
        weatherRepository.getPreviousQueries(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list: List<WeatherRoomModel> ->
                val array: List<String> = list.map { it.query }
                previousQueriesMutableLiveData.postValue(array)
            }
    }

}