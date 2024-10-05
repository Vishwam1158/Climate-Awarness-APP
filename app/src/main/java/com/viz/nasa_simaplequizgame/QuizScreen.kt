package com.viz.nasa_simaplequizgame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun QuizScreen(cityName: String, onQuizCompleted: () -> Unit, onShowTips: () -> Unit) {
    var guessAQI by remember { mutableStateOf(TextFieldValue("")) }
    var actualAQI by remember { mutableStateOf<CityAQI?>(null) }
    var showResult by remember { mutableStateOf(false) }

    val citiesData = loadAQIData() // Simulated JSON data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            actualAQI = citiesData.cities.find { it.name.equals(cityName, ignoreCase = true) }

            if (actualAQI != null) {
                Text("Guess the AQI for $cityName:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

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
            } else {
                Text("City not found in AQI data. Try another city.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))
            }
        } else {
            // Show Results
            actualAQI?.let {
                val guessedValue = guessAQI.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(it.AQI - guessedValue)

                Text("Actual AQI for ${it.name}: ${it.AQI} (${it.category})", style = MaterialTheme.typography.bodyLarge)
                Text("Your guess: $guessedValue", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: $difference", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(20.dp))

                // Button to show tips on the next screen
                Button(onClick = onShowTips) {
                    Text("Show Tips")
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Button to Restart Quiz
                Button(onClick = {
                    showResult = false
                    onQuizCompleted()
                }) {
                    Text("Try Another City")
                }
            }
        }
    }
}

@Composable
fun TipsScreen(onBackToQuiz: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tips to Improve Air Quality", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))

        // Tips content
        Text("1. Plant more trees.", style = MaterialTheme.typography.bodyLarge)
        Text("2. Reduce vehicle emissions.", style = MaterialTheme.typography.bodyLarge)
        Text("3. Use public transport.", style = MaterialTheme.typography.bodyLarge)
        Text("4. Avoid burning waste.", style = MaterialTheme.typography.bodyLarge)
        Text("5. Promote clean energy sources.", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(20.dp))

        // Back to Quiz Button
        Button(onClick = onBackToQuiz) {
            Text("Back to Quiz")
        }
    }
}



fun onQuizCompleted() {
    TODO("Not yet implemented")
}

