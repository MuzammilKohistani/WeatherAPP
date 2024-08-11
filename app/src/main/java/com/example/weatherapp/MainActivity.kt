package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			WeatherAppTheme {
				WeatherApp()
			}
		}
	}
}

@Composable
fun WeatherApp() {
	val viewModel: WeatherViewModel = viewModel()
	val weatherData by viewModel.weather.collectAsState()
	var city by remember { mutableStateOf("") }
	val apikey = "985c683b6becffe4b352ccb5a0d8288c"
	
	Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
	) {
		TextField(
				value = city,
				onValueChange = { city = it },
				label = { Text("Enter City") },
				modifier = Modifier.fillMaxWidth()
		)
		
		Spacer(modifier = Modifier.height(16.dp))
		
		Button(
				onClick = {
					// Call your API function to fetch the weather data using the city and apikey
					viewModel.fetchWeather(city, apikey)
				},
				modifier = Modifier.fillMaxWidth()
		) {
			Text("Get Weather")
		}
		
		Spacer(modifier = Modifier.height(16.dp))
		Text(
				text = weatherData?.toFormattedString() ?: "No data available",
				modifier = Modifier.fillMaxWidth(),
				textAlign = TextAlign.Center
		)
	}
}
