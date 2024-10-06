package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.runtime.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Composable
fun GGEInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Greenhouse Gas Emissions in your city",
        description = listOf(
            "◉ This measures how much bad gases (like carbon dioxide) go into the air.",
            "◉ These gases make the Earth warmer, which is not good.",
            "◉ Cars, factories, and power plants produce these gases."
        ),
        cityName = cityName,
        onNext = onNext
    )
}

@Composable
fun GGEGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadEmissionsData() // Load the emissions data
    val actualCO2 = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.co2?.toDouble()

    GuessScreen(
        title = "Guess the CO2 Emissions for $cityName",
        cityName = cityName,
        actualValue = actualCO2,  // Pass the CO2 emission value as Double
        onNext = onNext,
        placeholder = "Between 350 to 450"
    )
}


@Composable
fun GGETipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to Reduce Greenhouse Gas Emissions",
        tips = listOf(
            "◉ Use energy-efficient appliances.",
            "◉ Reduce vehicle emissions.",
            "◉ Switch to renewable energy.",
            "◉ Plant more trees.",
            "◉ Support public transport"
        ),
        cityName = cityName,
        onNext = onNext
    )
}



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
    {"name": "Mumbai", "CO2": 400, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "CO2": 420, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "CO2": 405, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "CO2": 410, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "CO2": 400, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "CO2": 405, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "CO2": 395, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "CO2": 380, "unit": "ppm", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesEmissionsData>() {}.type
    val citiesData: CitiesEmissionsData = gson.fromJson(jsonData, type)
    return citiesData.cities
}


