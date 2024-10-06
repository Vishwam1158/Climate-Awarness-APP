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
fun FinishScreen(onBackToHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Completed!", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBackToHome) {
            Text("Back to Home Screen")
        }
    }
}



//@Composable
//fun QuizScreenWQI(cityName: String, onNextQuiz: () -> Unit) {
//    var guessWQI by remember { mutableStateOf(TextFieldValue("")) }
//    var actualWQI by remember { mutableStateOf<CityWQI?>(null) }
//    var showResult by remember { mutableStateOf(false) }
//
//    val citiesData = loadWQIData() // Load WQI data
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        if (!showResult) {
//            actualWQI = citiesData.find { it.name.equals(cityName, ignoreCase = true) }
//
//            if (actualWQI != null) {
//                Text("Guess the WQI for $cityName:", style = MaterialTheme.typography.bodyLarge)
//                Spacer(modifier = Modifier.height(16.dp))
//
//                TextField(
//                    value = guessWQI,
//                    onValueChange = { guessWQI = it },
//                    label = { Text("Your WQI Guess") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Button(onClick = { showResult = true }) {
//                    Text("Submit Answer")
//                }
//            }
//        } else {
//            actualWQI?.let {
//                val guessedValue = guessWQI.text.toIntOrNull() ?: 0
//                val difference = kotlin.math.abs(it.WQI - guessedValue)
//
//                Text("Actual WQI for ${it.name}: ${it.WQI} (${it.category})", style = MaterialTheme.typography.bodyLarge)
//                Text("Your guess: $guessedValue", style = MaterialTheme.typography.bodyLarge)
//                Text("Difference: $difference", style = MaterialTheme.typography.bodyLarge)
//
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(onClick = onNextQuiz) {
//                    Text("Next Question: Temperature Variations")
//                }
//            }
//        }
//    }
//}

@Composable
fun QuizScreenTemperature(cityName: String, onNextQuiz: () -> Unit) {
    var guessTemp by remember { mutableStateOf(TextFieldValue("")) }
    var actualTemp by remember { mutableStateOf<CityTemperature?>(null) }
    var showResult by remember { mutableStateOf(false) }

    val citiesData = loadTemperatureData() // Load Temperature data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            actualTemp = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

            if (actualTemp != null) {
                Text("How much has the average temperature increased in $cityName?", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = guessTemp,
                    onValueChange = { guessTemp = it },
                    label = { Text("Your Temperature Guess (°C)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = { showResult = true }) {
                    Text("Submit Answer")
                }
            }
        } else {
            actualTemp?.let {
                val guessedValue = guessTemp.text.toFloatOrNull() ?: 0f
                val difference = kotlin.math.abs(it.temperatureChange - guessedValue)

                Text("Actual Temperature change for ${it.name}: ${it.temperatureChange}°C", style = MaterialTheme.typography.bodyLarge)
                Text("Your guess: $guessedValue°C", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: $difference°C", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = onNextQuiz) {
                    Text("See Tips")
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
        Text("Tips to Improve Environment", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))

        Text("1. Plant more trees.", style = MaterialTheme.typography.bodyLarge)
        Text("2. Reduce emissions by using public transport.", style = MaterialTheme.typography.bodyLarge)
        Text("3. Conserve water.", style = MaterialTheme.typography.bodyLarge)
        Text("4. Promote renewable energy sources.", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBackToQuiz) {
            Text("Back to Quiz")
        }
    }
}



fun onQuizCompleted() {
    TODO("Not yet implemented")
}

