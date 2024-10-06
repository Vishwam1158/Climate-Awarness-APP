package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.runtime.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Composable
fun REInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Renewable Energy Usage in your city",
        description = listOf(
            "◉ This is energy that comes from the sun, wind, or water.",
            "◉ Using more renewable energy helps the Earth stay healthy.",
            "◉ Some cities use more renewable energy than others."
        ),
        cityName = cityName,
        onNext = onNext
    )
}


@Composable
fun REGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadRenewableEnergyData()
    val actualRenewableEnergy = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.renewableEnergy

    GuessScreen(
        title = "Guess the Renewable Energy Usage for $cityName",
        cityName = cityName,
        actualValue = actualRenewableEnergy?.toDouble(),  // Pass the Double value
        onNext = onNext,
        placeholder = "Answer is between 5 and 30"
    )
}

@Composable
fun RETipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to Increase Renewable Energy Usage",
        tips = listOf(
            "◉ Install solar panels.",
            "◉ Promote wind and solar power.",
            "◉ Use energy-efficient appliances.",
            "◉ Support renewable energy policies.",
            "◉ Participate in local clean energy programs"
        ),
        cityName = cityName,
        onNext = onNext
    )
}



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
    {"name": "Mumbai", "renewableEnergy": 15, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "renewableEnergy": 12, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "renewableEnergy": 18, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "renewableEnergy": 14, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "renewableEnergy": 13, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "renewableEnergy": 9, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "renewableEnergy": 16, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "renewableEnergy": 6, "unit": "%", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesRenewableEnergyData>() {}.type
    val citiesData: CitiesRenewableEnergyData = gson.fromJson(jsonData, type)
    return citiesData.cities
}


