package com.example.weatherappkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappkotlin.model.WeatherDataModel
import com.example.weatherappkotlin.service.WeatherApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.lang.Error

class MainViewModel : ViewModel() {

    private val weatherApiService = WeatherApiService()
    private val disposable = CompositeDisposable()

    val weatherData = MutableLiveData<WeatherDataModel>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoading = MutableLiveData<Boolean>()

    fun refreshData(cityName: String) {
        getDataFromAPI(cityName)
    }

    private fun getDataFromAPI(cityName: String) {

        weatherLoading.value = true
        disposable.add(
            weatherApiService.getDataService(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherDataModel>() {

                    override fun onSuccess(t: WeatherDataModel) {
                        weatherData.value = t
                        weatherError.value = false
                        weatherLoading.value = false
                        Log.d("TAG", "onSuccess: Success")
                    }

                    override fun onError(e: Throwable) {
                        weatherError.value = true
                        weatherLoading.value = false
                        Log.e("TAG", "onError: " + e)
                    }

                })
        )

    }

}