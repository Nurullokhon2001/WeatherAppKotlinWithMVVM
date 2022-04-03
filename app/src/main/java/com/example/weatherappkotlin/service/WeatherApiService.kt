package com.example.weatherappkotlin.service

import com.example.weatherappkotlin.model.WeatherDataModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiService {

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("http://api.openweathermap.org/")
        .build()
        .create(WeatherApi::class.java)

    fun getDataService(cityName: String): Single<WeatherDataModel> {
        return api.getData(cityName)
    }
}