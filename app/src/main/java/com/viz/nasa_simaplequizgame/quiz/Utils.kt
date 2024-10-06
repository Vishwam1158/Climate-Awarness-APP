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

@Composable
fun InfoScreen(
    title: String,
    description: String,
    cityName: String,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = MaterialTheme.typography.bodySmall)
        Text(description.replace("[City]", cityName), style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNext) {
            Text("Next: Guess")
        }
    }
}


@Composable
fun GuessScreen(
    title: String,
    cityName: String,
    actualValue: Int?,  // The actual value of AQI, WQI, etc.
    onNext: () -> Unit
) {
    var guess by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess Phase
            Text(title, style = MaterialTheme.typography.bodySmall)

            TextField(
                value = guess,
                onValueChange = { guess = it },
                label = { Text("Your Guess") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { showResult = true }) {
                Text("Submit Guess")
            }
        } else {
            // Show results after guessing
            actualValue?.let { actual ->
                val guessedValue = guess.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(actual - guessedValue)

                Text("Actual Value for $cityName: $actual", style = MaterialTheme.typography.bodySmall)
                Text("Your guess: $guessedValue", style = MaterialTheme.typography.bodyLarge)
                Text("Difference: $difference", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Tips")
                }
            }
        }
    }
}


@Composable
fun TipsScreen(
    title: String,
    tips: List<String>,
    cityName: String,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = MaterialTheme.typography.bodySmall)
        Text("Here are some tips to improve in $cityName:")
        Spacer(modifier = Modifier.height(16.dp))

        // Display tips
        tips.forEach { tip ->
            Text(tip, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onNext) {
            Text("Next")
        }
    }
}


