package com.senri.weatherforecastapp.presentation.ui.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageFromURLWithPlaceHolder(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String,
    @DrawableRes placeholder: Int,
    contentScale: ContentScale = ContentScale.Crop
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        error = painterResource(placeholder),
        placeholder = painterResource(placeholder),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}