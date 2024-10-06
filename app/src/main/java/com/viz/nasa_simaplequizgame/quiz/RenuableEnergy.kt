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

data class CitiesRenewableEnergyData(
    @SerializedName("cities") val cities: List<CityRenewableEnergy>
)

data class CityRenewableEnergy(
    @SerializedName("name") val name: String,
    @SerializedName("renewableEnergy") val renewableEnergy: Int,
    @SerializedName("unit") val unit: String,
    @SerializedName("lastUpdated") val lastUpdated: String
)

fun loadRenewableEnergyData(): List<CityRenewableEnergy> {
    val jsonData = """
        {
  "cities": [
    {"name": "Ahmedabad", "renewableEnergy": 10, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "renewableEnergy": 12, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Vadodara", "renewableEnergy": 15, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Rajkot", "renewableEnergy": 14, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bhavnagar", "renewableEnergy": 9, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jamnagar", "renewableEnergy": 11, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Junagadh", "renewableEnergy": 8, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Anand", "renewableEnergy": 7, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mehsana", "renewableEnergy": 6, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nadiad", "renewableEnergy": 5, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    
    {"name": "Mumbai", "renewableEnergy": 15, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "renewableEnergy": 12, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "renewableEnergy": 18, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "renewableEnergy": 14, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "renewableEnergy": 13, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "renewableEnergy": 9, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "renewableEnergy": 16, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "renewableEnergy": 8, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "renewableEnergy": 11, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "renewableEnergy": 10, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "renewableEnergy": 7, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "renewableEnergy": 9, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "renewableEnergy": 12, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "renewableEnergy": 10, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "renewableEnergy": 6, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesRenewableEnergyData>() {}.type
    val citiesData: CitiesRenewableEnergyData = gson.fromJson(jsonData, type)
    return citiesData.cities
}

@Composable
fun REInfoScreen(cityName: String, onNext: () -> Unit) {
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
fun REGuessScreen(cityName: String, onNext: () -> Unit) {
    var guessRenewableEnergy by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }
    var actualRenewableEnergy by remember { mutableStateOf<CityRenewableEnergy?>(null) }

    // Load the actual renewable energy data for the city
    val citiesData = loadRenewableEnergyData()

    // Find the renewable energy usage for the selected city
    actualRenewableEnergy = citiesData.find { it.name.equals(cityName, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showResult) {
            // Guess Renewable Energy Usage Phase
            Text("Guess the Renewable Energy Usage for $cityName", style = MaterialTheme.typography.h5)

            TextField(
                value = guessRenewableEnergy,
                onValueChange = { guessRenewableEnergy = it },
                label = { Text("Your Guess (%)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                showResult = true  // Show result after submitting
            }) {
                Text("Submit Guess")
            }
        } else {
            // Display actual renewable energy usage and difference after the guess
            actualRenewableEnergy?.let { energy ->
                val guessedValue = guessRenewableEnergy.text.toIntOrNull() ?: 0
                val difference = kotlin.math.abs(energy.renewableEnergy - guessedValue)

                Text("Actual Renewable Energy Usage for ${energy.name}: ${energy.renewableEnergy}${energy.unit}", style = MaterialTheme.typography.h5)
                Text("Your guess: $guessedValue${energy.unit}", style = MaterialTheme.typography.body1)
                Text("Difference: $difference${energy.unit}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Renewable Energy Tips")
                }
            } ?: run {
                // In case actual renewable energy data is not found for the city
                Text("Renewable energy data not available for $cityName", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onNext) {
                    Text("Next: Renewable Energy Tips")
                }
            }
        }
    }
}

@Composable
fun RETipsScreen(cityName: String, onNext: () -> Unit) {
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

