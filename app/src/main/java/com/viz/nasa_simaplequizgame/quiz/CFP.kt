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

data class CitiesCarbonFootprintData(
    @SerializedName("cities") val cities: List<CityCarbonFootprint>
)

data class CityCarbonFootprint(
    @SerializedName("name") val name: String,
    @SerializedName("carbonFootprint") val carbonFootprint: Double,
    @SerializedName("unit") val unit: String,
    @SerializedName("lastUpdated") val lastUpdated: String
)

fun loadCarbonFootprintData(): List<CityCarbonFootprint> {
    val jsonData = """
        {
  "cities": [
    {"name": "Ahmedabad", "carbonFootprint": 1.9, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "carbonFootprint": 2.1, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Vadodara", "carbonFootprint": 2.0, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Rajkot", "carbonFootprint": 1.8, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bhavnagar", "carbonFootprint": 1.6, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jamnagar", "carbonFootprint": 2.3, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Junagadh", "carbonFootprint": 1.5, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Anand", "carbonFootprint": 1.7, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mehsana", "carbonFootprint": 1.6, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nadiad", "carbonFootprint": 1.5, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    
    {"name": "Mumbai", "carbonFootprint": 2.2, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "carbonFootprint": 2.5, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "carbonFootprint": 2.0, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "carbonFootprint": 2.1, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "carbonFootprint": 2.2, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "carbonFootprint": 1.9, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "carbonFootprint": 2.0, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "carbonFootprint": 1.8, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "carbonFootprint": 1.7, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "carbonFootprint": 1.9, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "carbonFootprint": 1.6, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "carbonFootprint": 1.7, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "carbonFootprint": 1.8, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "carbonFootprint": 1.9, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "carbonFootprint": 1.5, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesCarbonFootprintData>() {}.type
    val citiesData: CitiesCarbonFootprintData = gson.fromJson(jsonData, type)
    return citiesData.cities
}

@Composable
fun CFPInfoScreen(cityName: String, onNext: () -> Unit) {
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
fun CFPGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessCarbonFootprint by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualCarbonFootprint by remember { mutableStateOf<CityCarbonFootprint?>(null) }

    // Load the actual carbon footprint data for the city
    val citiesData = loadCarbonFootprintData()

    // Find the carbon footprint for the selected city
    actualCarbonFootprint = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess Carbon Footprint Phase
            Text("Guess the Carbon Footprint per Capita for $cityName", style = MaterialTheme.typography.h5)

            TextField(
                value = guessCarbonFootprint,
                onValueChange = { guessCarbonFootprint = it },
                label = { Text("Your Guess (tons CO2/person/year)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual carbon footprint and difference after the guess
            actualCarbonFootprint?.let { footprint ->
                val guessedValue = guessCarbonFootprint.text.toDoubleOrNull() ?: 0.0
                val difference = kotlin.math.abs(footprint.carbonFootprint - guessedValue)

                Text("Actual Carbon Footprint for ${footprint.name}: ${footprint.carbonFootprint} ${footprint.unit}", style = MaterialTheme.typography.h5)
                Text("Your guess: $guessedValue ${footprint.unit}", style = MaterialTheme.typography.body1)
                Text("Difference: ${"%.2f".format(difference)} ${footprint.unit}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Carbon Reduction Tips")
                }
            } ?: run {
                // In case actual carbon footprint data is not found for the city
                Text("Carbon footprint data not available for $cityName", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Carbon Reduction Tips")
                }
            }
        }
    }
}

@Composable
fun CFPTipsScreen(cityName: String, onNext: () -> Unit) {
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
