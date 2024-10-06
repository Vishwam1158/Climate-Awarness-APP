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
import com.viz.nasa_simaplequizgame.CitiesWQIData
import com.viz.nasa_simaplequizgame.CityAQI
import com.viz.nasa_simaplequizgame.CityWQI
import com.viz.nasa_simaplequizgame.loadAQIData
import com.viz.nasa_simaplequizgame.loadWQIData
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

@Composable
fun WQIInfoScreen(cityName: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Water Quality Index (WQI)", style = MaterialTheme.typography.bodySmall)
        Text("The AQI measures how clean or polluted the air is in $cityName.")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNext) {
            Text("Next: Guess AQI")
        }
    }
}






@Composable
fun WQIGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessWQI by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualWQI by remember { mutableStateOf<CityWQI?>(null) }

    // Load the actual WQI data for the city
    val citiesData = loadWQIData()

    // Find the WQI for the selected city
    actualWQI = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess WQI Phase
            Text("Guess the Water Quality Index (WQI) for $cityName", style = MaterialTheme.typography.bodySmall)

            TextField(
                value = guessWQI,
                onValueChange = { guessWQI = it },
                label = { Text("Your WQI Guess (0-100)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual WQI and difference after the guess
            actualWQI?.let { wqi ->
                val guessedValue = guessWQI.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(wqi.wqi - guessedValue)

                Text("Actual WQI for ${wqi.name}: ${wqi.wqi} (${wqi.category})", style = MaterialTheme.typography.bodySmall)
                Text("Your guess: $guessedValue", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: $difference", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Water Quality Tips")
                }
            } ?: run {
                // In case actual WQI is not found for the city
                Text("WQI data not available for $cityName", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Water Quality Tips")
                }
            }
        }
    }
}

@Composable
fun WQITipsScreen(cityName: String, onNext: () -> Unit) {
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
