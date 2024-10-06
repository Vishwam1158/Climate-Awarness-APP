package com.viz.nasa_simaplequizgame


import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import okhttp3.Credentials
import okhttp3.Interceptor

interface MeteomaticsApi {
    @GET("{time}/{parameter}/{lat},{lon}/json")
    fun getClimateData(
        @Path("time") time: String,
        @Path("parameter") parameter: String,
        @Path("lat") lat: String,
        @Path("lon") lon: String
    ): Call<ClimateResponse>
}



object ApiClient {

    private val username = "sutariya_ashlesh"  // Replace with your Meteomatics username
    private val password = "l1ExkM7G6g"  // Replace with your Meteomatics password

    private val authInterceptor = Interceptor { chain ->
        val credentials = Credentials.basic(username, password)
        val newRequest = chain.request().newBuilder()
            .header("Authorization", credentials)
            .build()
        chain.proceed(newRequest)
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.meteomatics.com/")
            .client(httpClient)  // Apply the HTTP client with Basic Auth
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: MeteomaticsApi by lazy {
        retrofit.create(MeteomaticsApi::class.java)
    }
}

data class ClimateResponse(
    val data: List<ClimateData>
)

data class ClimateData(
    val coordinates: Coordinates,
    val values: List<Value>
)

data class Coordinates(
    val lat: Double,
    val lon: Double
)

data class Value(
    val date: String,
    val value: Double
)
