package com.senri.weatherforecastapp.presentation.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun ImageFromURLWithPlaceHolder(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String,
    @DrawableRes placeholder: Int
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        error = painterResource(placeholder),
        placeholder = painterResource(placeholder),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}