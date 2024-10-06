package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.runtime.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Composable
fun WasteGenerationInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Waste Generation Info",
        description = listOf(
            "◉ This tells us how much trash each person makes in a day.",
            "◉ People in big cities usually make more trash than those in small towns." ,
            "◉ It's important to recycle and throw trash properly to keep cities clean."
        ),
        cityName = cityName,
        onNext = onNext
    )
}


@Composable
fun WasteGenerationGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadWasteGenerationData() // Load the data for waste generation
    val actualWasteGeneration = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.wasteGeneration

    GuessScreen(
        title = "Guess the Waste Generation for $cityName",
        cityName = cityName,
        actualValue = actualWasteGeneration,  // Pass the Double value directly
        onNext = onNext,
        placeholder = "Between 0.3 to 1.5"
    )
}



@Composable
fun WasteGenerationTipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to Reduce Waste Generation",
        tips = listOf(
            "◉ Reduce, reuse, and recycle materials.",
            "◉ Compost organic waste.",
            "◉ Use reusable bags and bottles",
            "◉ Avoid single-use plastics.",
            "◉ Buy in bulk to reduce packaging."
        ),
        cityName = cityName,
        onNext = onNext
    )
}




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
    {"name": "Mumbai", "wasteGeneration": 0.8, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "wasteGeneration": 0.9, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "wasteGeneration": 0.7, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "wasteGeneration": 0.6, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "wasteGeneration": 0.7, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "wasteGeneration": 0.68, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "wasteGeneration": 0.65, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "wasteGeneration": 0.45, "unit": "kg/person/day", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesWasteData>() {}.type
    val citiesData: CitiesWasteData = gson.fromJson(jsonData, type)
    return citiesData.cities
}
