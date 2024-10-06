package com.viz.nasa_simaplequizgame.gif

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.unit.dp
import com.viz.nasa_simaplequizgame.R
import coil.decode.ImageDecoderDecoder

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun HomeGIF() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // Make card background transparent if needed
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.gif1) // Replace with your local GIF resource
                .decoderFactory(ImageDecoderDecoder.Factory()) // Use ImageDecoder for animated GIFs
                .build(),
            contentDescription = "Local GIF",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f) // Adjust aspect ratio as needed
        )
    }
}

