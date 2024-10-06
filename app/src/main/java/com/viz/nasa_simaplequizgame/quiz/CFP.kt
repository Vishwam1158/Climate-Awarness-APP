package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.runtime.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Composable
fun CFPInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Carbon Footprint in your city",
        description = listOf("" +
                "◉ This tells us how much pollution each person makes.\n" +
                "◉ Cities with lots of cars and factories have bigger footprints.\n" +
                "◉ We can make our footprints smaller by walking or using renewable energy."),
        cityName = cityName,
        onNext = onNext
    )
}

@Composable
fun CFPGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadCarbonFootprintData() // Load the carbon footprint data
    val actualCarbonFootprint = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.carbonFootprint

    GuessScreen(
        title = "Guess the Carbon Footprint per Capita for $cityName",
        cityName = cityName,
        actualValue = actualCarbonFootprint,  // Pass the carbon footprint as Double
        onNext = onNext,
        placeholder = "Between 2 to 15 tons CO2/person/year"
    )
}

@Composable
fun CFPTipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to Reduce Carbon Footprint",
        tips = listOf(
            "◉ Reduce, Reuse, Recycle",
            "◉ Switch to renewable energy.",
            "◉ Use public transportation.",
            "◉ Reduce energy consumption.",
            "◉ Buy local products.",
//            "Educate yourself and others"
        ),
        cityName = cityName,
        onNext = onNext
    )
}



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
    
    {"name": "Mumbai", "carbonFootprint": 2.2, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "carbonFootprint": 2.5, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "carbonFootprint": 2.0, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "carbonFootprint": 2.1, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "carbonFootprint": 2.2, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "carbonFootprint": 1.9, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "carbonFootprint": 2.0, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "carbonFootprint": 1.5, "unit": "tons CO2/person/year", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesCarbonFootprintData>() {}.type
    val citiesData: CitiesCarbonFootprintData = gson.fromJson(jsonData, type)
    return citiesData.cities
}

