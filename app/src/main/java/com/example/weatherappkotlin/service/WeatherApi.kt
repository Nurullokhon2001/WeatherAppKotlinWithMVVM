package com.example.weatherappkotlin.service

import com.example.weatherappkotlin.model.WeatherDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?&units=metric&&APPID=da0f8dee0d99fed9106437bf05e879ad")
    fun getData(
        @Query("q") cityName: String
    ): Single<WeatherDataModel>

}