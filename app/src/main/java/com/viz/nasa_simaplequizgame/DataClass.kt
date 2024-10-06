package com.viz.nasa_simaplequizgame

import com.google.gson.annotations.SerializedName

// Data class for AQI
data class CitiesData(
    val cities: List<CityAQI>
)

data class CityAQI(
    val name: String,
    val AQI: Int,
    val category: String,
    val lastUpdated: String
)



// Data class for Temperature Change
data class CitiesTemperatureData(
    @SerializedName("cities") val cities: List<CityTemperature>
)

data class CityTemperature(
    @SerializedName("name") val name: String,
    @SerializedName("temperatureChange") val temperatureChange: Float,
    @SerializedName("unit") val unit: String,
    @SerializedName("lastUpdated") val lastUpdated: String
)

// Data class for WQI
data class CitiesWQIData(
    @SerializedName("cities") val cities: List<CityWQI>
)

data class CityWQI(
    @SerializedName("name") val name: String,
    @SerializedName("WQI") val wqi: Int,
    @SerializedName("category") val category: String,
    @SerializedName("lastUpdated") val lastUpdated: String
)
