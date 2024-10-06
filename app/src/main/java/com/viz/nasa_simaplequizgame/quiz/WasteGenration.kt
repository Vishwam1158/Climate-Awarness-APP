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

data class CitiesWasteData(
    @SerializedName("cities") val cities: List<CityWaste>
)

data class CityWaste(
    @SerializedName("name") val name: String,
    @SerializedName("wasteGeneration") val wasteGeneration: Double,
    @SerializedName("unit") val unit: String,
    @SerializedName("lastUpdated") val lastUpdated: String
)

fun loadWasteGenerationData(): List<CityWaste> {
    val jsonData = """
        {
  "cities": [
    {"name": "Ahmedabad", "wasteGeneration": 0.65, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "wasteGeneration": 0.55, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Vadodara", "wasteGeneration": 0.60, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Rajkot", "wasteGeneration": 0.52, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bhavnagar", "wasteGeneration": 0.50, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jamnagar", "wasteGeneration": 0.48, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Junagadh", "wasteGeneration": 0.45, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Anand", "wasteGeneration": 0.43, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mehsana", "wasteGeneration": 0.40, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nadiad", "wasteGeneration": 0.38, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    
    {"name": "Mumbai", "wasteGeneration": 0.8, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "wasteGeneration": 0.9, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "wasteGeneration": 0.7, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "wasteGeneration": 0.6, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "wasteGeneration": 0.7, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "wasteGeneration": 0.68, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "wasteGeneration": 0.65, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "wasteGeneration": 0.5, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "wasteGeneration": 0.55, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "wasteGeneration": 0.58, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "wasteGeneration": 0.48, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "wasteGeneration": 0.4, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "wasteGeneration": 0.5, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "wasteGeneration": 0.56, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "wasteGeneration": 0.45, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesWasteData>() {}.type
    val citiesData: CitiesWasteData = gson.fromJson(jsonData, type)
    return citiesData.cities
}


@Composable
fun WasteGenerationInfoScreen(cityName: String, onNext: () -> Unit) {
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
fun WasteGenerationGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessWasteGeneration by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualWasteGeneration by remember { mutableStateOf<CityWaste?>(null) }

    // Load the actual waste generation data for the city
    val citiesData = loadWasteGenerationData()

    // Find the waste generation for the selected city
    actualWasteGeneration = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess Waste Generation Phase
            Text("Guess the Waste Generation for $cityName", style = MaterialTheme.typography.h5)

            TextField(
                value = guessWasteGeneration,
                onValueChange = { guessWasteGeneration = it },
                label = { Text("Your Guess (kg/person/day)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual waste generation and difference after the guess
            actualWasteGeneration?.let { waste ->
                val guessedValue = guessWasteGeneration.text.toDoubleOrNull() ?: 0.0
                val difference = kotlin.math.abs(waste.wasteGeneration - guessedValue)

                Text("Actual Waste Generation for ${waste.name}: ${waste.wasteGeneration} ${waste.unit}", style = MaterialTheme.typography.h5)
                Text("Your guess: $guessedValue ${waste.unit}", style = MaterialTheme.typography.body1)
                Text("Difference: ${"%.2f".format(difference)} ${waste.unit}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Waste Reduction Tips")
                }
            } ?: run {
                // In case actual waste generation data is not found for the city
                Text("Waste generation data not available for $cityName", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Waste Reduction Tips")
                }
            }
        }
    }
}


@Composable
fun WasteGenerationTipsScreen(cityName: String, onNext: () -> Unit) {
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
