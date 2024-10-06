package com.viz.nasa_simaplequizgame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.viz.nasa_simaplequizgame.quiz.WorldImageBox
import com.viz.nasa_simaplequizgame.quiz.loadAQIData

@Composable
fun CityInputScreen(onSubmitCity: (String) -> Unit) {
    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    var showSuggestions by remember { mutableStateOf(true) } // Control when to show suggestions
    val citiesData = loadAQIData() // Load JSON Data, assuming this returns List<CityAQI>

    val suggestions = remember(cityName.text) {
        if (cityName.text.isNotEmpty() && showSuggestions) {
            citiesData.filter { city ->
                city.name.startsWith(cityName.text, ignoreCase = true)  // Ensure suggestion only matches start of string
            }
        } else {
            emptyList()
        }
    }
    WorldImageBox()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter Your City Name:",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF6151C3)
            )
        Spacer(modifier = Modifier.height(16.dp))

        // City Name Input Field
        CustomOutlinedTextField(
            value = cityName,
            onValueChange = {
                cityName = it
                showSuggestions = true // Show suggestions when typing
            },
            placeholder = "City Name"
        )
//        )
        Spacer(modifier = Modifier.height(8.dp))

        // Show Suggestions below the TextField
        if (suggestions.isNotEmpty()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                suggestions.forEach { city ->  // Explicitly reference CityAQI in forEach
                    CitySuggestionItem(cityName = city.name, onSelectCity = {
                        // Set the selected city name into the TextField
                        cityName = TextFieldValue(it)
                        showSuggestions = false // Hide suggestions after selecting a city
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Submit Button
        CustomButton(
            text = "Submit",
            onClick = { onSubmitCity(cityName.text) }
        )
        
    }
}

@Composable
fun CitySuggestionItem(cityName: String, onSelectCity: (String) -> Unit) {
    Text(
        text = cityName,
        color = Color(0xFF6151C3),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onSelectCity(cityName)
            }
    )
}


@Preview
@Composable
private fun CityInputScreenPreview() {
    CityInputScreen {

    }
}



@Composable
fun CustomOutlinedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
) {


    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
//            label = {Text(text = label)},
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF6151C3)
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF6151C3),
                unfocusedBorderColor = Color(0xFF6151C3),
                cursorColor = Color(0xFF6151C3),
                focusedLabelColor = Color(0xFF6151C3),
                unfocusedLabelColor = Color(0xFF6151C3),
                focusedTextColor = Color(0xFF6151C3),
                focusedContainerColor = Color.White,

                ),

            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )
    }
}
