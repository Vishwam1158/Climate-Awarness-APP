package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.viz.nasa_simaplequizgame.CustomButton
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.viz.nasa_simaplequizgame.CustomOutlinedTextField
import com.viz.nasa_simaplequizgame.R


@Composable
fun InfoScreen(
    title: String,
    description: List<String>,
    cityName: String,
    onNext: () -> Unit
) {
    WorldImageBox()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleCard(title = title)
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .wrapContentSize()  // The card will adjust its size based on content
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF8174CF)),
            shape = RoundedCornerShape(20.dp) // Rounded corners for a smoother appearance
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()  // The column will also adjust its size based on content
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start // Left align text
            ) {
                description.forEach { desc ->
                    Text(
                        text = desc.replace("[City]", cityName),
                        fontSize = 20.sp,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    text = "Next",
                    onClick = onNext
                )
            }
        }
//    }
}

@Composable
fun GuessScreen(
    title: String,
    cityName: String,
    actualValue: Double?,
    onNext: () -> Unit,
    placeholder: String = "Your Guess"
) {
    var guess by remember { mutableStateOf(TextFieldValue("")) }
    var showResult by remember { mutableStateOf(false) }

    WorldImageBox()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!showResult) {
            // Guess Phase
            Spacer(modifier = Modifier.height(20.dp))
            TitleCard(title = title)
            CustomOutlinedTextField(
                value = guess,
                onValueChange = { guess = it },
                placeholder = placeholder,

            )
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    text = "Submit Guess",
                    onClick = { showResult = true}
                )
            }
        } else {
            // Show results after guessing
            actualValue?.let { actual ->
                val guessedValue = guess.text.toDoubleOrNull() ?: 0.0  // Parse guess as Double
                val difference = kotlin.math.abs(actual - guessedValue)

                TitleCard(title = "Actual Value for $cityName: $actual\"")
                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = "Your guess: $guessedValue",
                    fontSize = 16.sp, // Custom font size
                    color = Color(0xFF6151C3)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Difference: ${"%.2f".format(difference)}",
                    fontSize = 16.sp, // Custom font size
                    color = Color(0xFF6151C3)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    CustomButton(
                        text = "Next: Tips",
                        onClick = onNext
                    )
                }
            }
        }
    }
}



@Composable
fun TipsScreen(
    title: String,
    tips: List<String>,
    cityName: String,
    onNext: () -> Unit
) {
    WorldImageBox()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleCard(title = title)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "How can you contribute : ",//"How to contribute to improve in $cityName:",
            fontSize = 24.sp, // Custom font size
            color = Color(0xFF6151C3)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier
                .wrapContentSize()  // The card will adjust its size based on content
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF8174CF)),
            shape = RoundedCornerShape(20.dp) // Rounded corners for a smoother appearance
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()  // The column will also adjust its size based on content
                    .padding(16.dp),
//                    .background(Color(0xFF6151C3)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start // Left align text
            ) {


                tips.forEach { tip ->
                    Text(
                        text = tip,
                        fontSize = 20.sp, // Custom font size
                        color = Color.White                    )
                }

            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            CustomButton(
                text = "Next",
                onClick = onNext
            )
        }
    }
}


@Composable
fun TitleCard(title: String) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF6151C3)),
        shape = RoundedCornerShape(16.dp)
    ) {
            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
            )
    }
}

@Composable
fun WorldImageBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.world),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

