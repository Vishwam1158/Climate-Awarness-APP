package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.viz.nasa_simaplequizgame.CityTemperature
import com.viz.nasa_simaplequizgame.loadTemperatureData

@Composable
fun TVInfoScreen(cityName: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Temperature Variations in last 10 years in your city", style = MaterialTheme.typography.bodySmall)
        Text("How much has the temperature increased over the last 10 years in $cityName.")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNext) {
            Text("Next: Guess Temperature Variations")
        }
    }
}

@Composable
fun TVGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessTemperature by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualTemperature by remember { mutableStateOf<CityTemperature?>(null) }

    // Load the actual temperature data for the city
    val citiesData = loadTemperatureData()

    // Find the temperature change for the selected city
    actualTemperature = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess Temperature Change Phase
            Text("Guess the temperature change for $cityName", style = MaterialTheme.typography.bodySmall)

            TextField(
                value = guessTemperature,
                onValueChange = { guessTemperature = it },
                label = { Text("Your Temperature Change Guess (°C)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual temperature change and difference after the guess
            actualTemperature?.let { temp ->
                val guessedValue = guessTemperature.text.toFloatOrNull() ?: 0f
                val difference = kotlin.math.abs(temp.temperatureChange - guessedValue)

                Text("Actual temperature change for ${temp.name}: ${temp.temperatureChange}${temp.unit}", style = MaterialTheme.typography.bodySmall)
                Text("Your guess: $guessedValue°C", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: ${"%.1f".format(difference)}°C", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Climate Change Tips")
                }
            } ?: run {
                // In case actual temperature data is not found for the city
                Text("Temperature data not available for $cityName", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Climate Change Tips")
                }
            }
        }
    }
}
@Composable
fun TVTipsScreen(cityName: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("How to Improve AQI", style = MaterialTheme.typography.bodySmall)
        Text("Here are some tips to improve air quality in $cityName:")
        Text("1. Plant more trees.")
        Text("2. Reduce vehicle emissions.")
        Text("3. Use public transportation.")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNext) {
            Text("Next")
        }
    }
}
