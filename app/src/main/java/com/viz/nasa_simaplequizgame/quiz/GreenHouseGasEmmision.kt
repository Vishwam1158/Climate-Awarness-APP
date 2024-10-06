package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class CitiesEmissionsData(
    @SerializedName("cities") val cities: List<CityEmissions>
)

data class CityEmissions(
    @SerializedName("name") val name: String,
    @SerializedName("CO2") val co2: Int,
    @SerializedName("unit") val unit: String,
    @SerializedName("lastUpdated") val lastUpdated: String
)

fun loadEmissionsData(): List<CityEmissions> {
    val jsonData = """
        {
  "cities": [
    {"name": "Ahmedabad", "CO2": 415, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "CO2": 410, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Vadodara", "CO2": 405, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Rajkot", "CO2": 400, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bhavnagar", "CO2": 395, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jamnagar", "CO2": 390, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Junagadh", "CO2": 388, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Anand", "CO2": 385, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mehsana", "CO2": 380, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nadiad", "CO2": 375, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    
    {"name": "Mumbai", "CO2": 400, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "CO2": 420, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "CO2": 405, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "CO2": 410, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "CO2": 400, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "CO2": 405, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "CO2": 395, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "CO2": 390, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "CO2": 395, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "CO2": 400, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "CO2": 385, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "CO2": 380, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "CO2": 395, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "CO2": 388, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "CO2": 380, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesEmissionsData>() {}.type
    val citiesData: CitiesEmissionsData = gson.fromJson(jsonData, type)
    return citiesData.cities
}

@Composable
fun GGEInfoScreen(cityName: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            "Air Quality Index (AQI)",
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
        )
        androidx.compose.material3.Text("The AQI measures how clean or polluted the air is in $cityName.")
        Spacer(modifier = Modifier.height(20.dp))

        androidx.compose.material3.Button(onClick = onNext) {
            androidx.compose.material3.Text("Next: Guess AQI")
        }
    }
}


@Composable
fun GGEGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessCO2 by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualCO2 by remember { mutableStateOf<CityEmissions?>(null) }

    // Load the actual CO2 emission data for the city
    val citiesData = loadEmissionsData()

    // Find the CO2 emissions for the selected city
    actualCO2 = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess CO2 Emissions Phase
            Text("Guess the CO2 Emissions for $cityName", style = MaterialTheme.typography.h5)

            TextField(
                value = guessCO2,
                onValueChange = { guessCO2 = it },
                label = { Text("Your Guess (ppm)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual CO2 emissions and difference after the guess
            actualCO2?.let { emissions ->
                val guessedValue = guessCO2.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(emissions.co2 - guessedValue)

                Text("Actual CO2 Emissions for ${emissions.name}: ${emissions.co2} ${emissions.unit}", style = MaterialTheme.typography.h5)
                Text("Your guess: $guessedValue ${emissions.unit}", style = MaterialTheme.typography.body1)
                Text("Difference: $difference ${emissions.unit}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Emission Reduction Tips")
                }
            } ?: run {
                // In case actual CO2 emission data is not found for the city
                Text("CO2 emission data not available for $cityName", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Emission Reduction Tips")
                }
            }
        }
    }
}

@Composable
fun GGETipsScreen(cityName: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            "How to Improve AQI",
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
        )
        androidx.compose.material3.Text("Here are some tips to improve air quality in $cityName:")
        androidx.compose.material3.Text("1. Plant more trees.")
        androidx.compose.material3.Text("2. Reduce vehicle emissions.")
        androidx.compose.material3.Text("3. Use public transportation.")
        Spacer(modifier = Modifier.height(20.dp))

        androidx.compose.material3.Button(onClick = onNext) {
            androidx.compose.material3.Text("Next")
        }
    }
}

