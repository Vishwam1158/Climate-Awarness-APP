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
import com.viz.nasa_simaplequizgame.CityAQI
import com.viz.nasa_simaplequizgame.loadAQIData


@Composable
fun AQIInfoScreen(cityName: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Air Quality Index (AQI)", style = MaterialTheme.typography.bodySmall)
        Text("The AQI measures how clean or polluted the air is in $cityName.")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNext) {
            Text("Next: Guess AQI")
        }
    }
}

@Composable
fun AQIGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessAQI by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualAQI by remember { mutableStateOf<CityAQI?>(null) }  // Assuming CityAQI has a field 'AQI'

    // Load the actual AQI data for the city (you can replace with your data loading logic)
    val citiesData = loadAQIData()  // Ensure this loads List<CityAQI>

    // Find the AQI for the selected city
    actualAQI = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess AQI Phase
            Text("Guess the AQI for $cityName", style = MaterialTheme.typography.bodySmall)

            TextField(
                value = guessAQI,
                onValueChange = { guessAQI = it },
                label = { Text("Your AQI Guess") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual AQI and difference after the guess
            actualAQI?.let { aqi ->
                val guessedValue = guessAQI.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(aqi.AQI - guessedValue)

                Text("Actual AQI for ${aqi.name}: ${aqi.AQI} (${aqi.category})", style = MaterialTheme.typography.bodySmall)
                Text("Your guess: $guessedValue", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: $difference", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: AQI Tips")
                }
            } ?: run {
                // In case actual AQI is not found for the city
                Text("AQI data not available for $cityName", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: AQI Tips")
                }
            }
        }
    }
}


@Composable
fun AQITipsScreen(cityName: String, onNext: () -> Unit) {
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


@Composable
fun QuizScreenAQI(cityName: String, onNextQuiz: () -> Unit) {
    var guessAQI by remember { mutableStateOf(TextFieldValue("")) }
    var actualAQI by remember { mutableStateOf<CityAQI?>(null) }  // Nullable CityAQI
    var showResult by remember { mutableStateOf(false) }

    // Use correct load function
    val citiesData = loadAQIData() // Ensure only one function is defined

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Find city based on cityName input
            actualAQI = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

            if (actualAQI != null) {
                Text("Guess the AQI for $cityName:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                // Input for AQI guess
                TextField(
                    value = guessAQI,
                    onValueChange = { guessAQI = it },
                    label = { Text("Your AQI Guess") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Submit Answer Button
                Button(onClick = { showResult = true }) {
                    Text("Submit Answer")
                }
            }
        } else {
            actualAQI?.let { aqi -> // Use 'aqi' as a reference inside the let block
                val guessedValue = guessAQI.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(aqi.AQI - guessedValue)

                Text("Actual AQI for ${aqi.name}: ${aqi.AQI} (${aqi.category})", style = MaterialTheme.typography.bodyLarge)
                Text("Your guess: $guessedValue", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: $difference", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = onNextQuiz) {
                    Text("Next Question: WQI")
                }
            }
        }
    }
}


