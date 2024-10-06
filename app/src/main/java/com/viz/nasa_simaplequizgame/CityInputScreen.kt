package com.viz.nasa_simaplequizgame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CityInputScreen(onSubmitCity: (String) -> Unit) {
    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    var showSuggestions by remember { mutableStateOf(true) } // Control when to show suggestions
    val citiesData = loadAQIData() // Load JSON Data, assuming this returns List<CityAQI>

    // Filter cities based on user input
    val suggestions = remember(cityName.text) {
        if (cityName.text.isNotEmpty() && showSuggestions) {
            citiesData.filter { city ->
                city.name.startsWith(cityName.text, ignoreCase = true)  // Ensure suggestion only matches start of string
            }
        } else {
            emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter Your City Name:", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // City Name Input Field
        TextField(
            value = cityName,
            onValueChange = {
                cityName = it
                showSuggestions = true // Show suggestions when typing
            },
            label = { Text("City Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Show Suggestions below the TextField
        if (suggestions.isNotEmpty()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                suggestions.forEach { city ->  // Explicitly reference CityAQI in forEach
                    CitySuggestionItem(cityName = city.name, onSelectCity = {
                        // Set the selected city name into the TextField
                        cityName = TextFieldValue(it)
                        showSuggestions = false // Hide suggestions after selecting a city
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Submit Button
        Button(onClick = { onSubmitCity(cityName.text) }) {
            Text("Submit")
        }
    }
}

@Composable
fun CitySuggestionItem(cityName: String, onSelectCity: (String) -> Unit) {
    Text(
        text = cityName,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onSelectCity(cityName) // When clicked, fill the text field with the city name
            }
    )
}
