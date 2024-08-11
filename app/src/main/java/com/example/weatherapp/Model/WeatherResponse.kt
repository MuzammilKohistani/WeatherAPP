package com.example.weatherapp.Model

data class WeatherResponse(
		val main : Main, val weather : List<Weather>
) {
	data class Main(val temp : Float, val pressure : Int, val humidity : Int)
	data class Weather(val description : String, val icon : String)
	
	fun toFormattedString() : String {
		val temp = main.temp
		val pressure = main.pressure
		val humidity = main.humidity
		val description = weather.joinToString(", ") { it.description }
		
		return "Temperature: $temp°C\nPressure: $pressure hPa\nHumidity: $humidity%\nWeather: $description"
	}
}


