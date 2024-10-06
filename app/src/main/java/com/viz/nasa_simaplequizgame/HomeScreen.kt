package com.viz.nasa_simaplequizgame

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.viz.nasa_simaplequizgame.gif.HomeGIF

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeScreen(onStartQuiz: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        HomeGIF()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 336.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "   Climate\nAwareness\n     Game",
//                style = MaterialTheme.typography.headlineSmall,
                fontSize = 35.sp,
                color = Color.White
//                textAlign = Alignment.Center
            )
            Spacer(modifier = Modifier.height(40.dp))

            // Start Quiz Button

            CustomButton(
                text = "Learn Play Aware",
                fontSize = 20.sp,
                textColor = Color(0xFF6151C3),
                backgroundColor = Color.White,
                fontWeight = FontWeight.Bold,
                onClick = onStartQuiz)
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    fontSize: TextUnit = 20.sp,
    textColor: Color = Color.White,
    backgroundColor: Color = Color(0xFF6151C3),
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(20.dp) // Rectangle shape
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = fontSize
            ),
            modifier = Modifier.padding(4.dp),
            fontWeight = fontWeight,
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
private fun HomeScreenPrev() {
//    HomeScreen {

//    }
}