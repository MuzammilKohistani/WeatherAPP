package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Model.WeatherApi
import com.example.weatherapp.Model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
	private val _weather = MutableStateFlow<WeatherResponse?>(null)
	val weather : MutableStateFlow<WeatherResponse?> = _weather
	private val weatherApi = WeatherApi.create()
	
	fun fetchWeather(cityName : String,apiKey : String) {
		viewModelScope.launch {
			try {
				val response = weatherApi.getWeather(cityName, apiKey)
				_weather.value = response
			} catch (e : Exception) {
				_weather.value = null
			}
		}
	}
	
}
