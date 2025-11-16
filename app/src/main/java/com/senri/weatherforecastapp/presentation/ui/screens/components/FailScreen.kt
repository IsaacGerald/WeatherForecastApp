package com.senri.weatherforecastapp.presentation.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.presentation.theme.PrimaryBlack


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message:String = "",
    onRetry: () -> Unit
){

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            text = message,
            style = MaterialTheme.typography.displayMedium,
            color = Color.White.copy(alpha = 0.3f),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButton(
                height = 35.dp,
                buttonColor = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(40.dp),
                buttonText = stringResource(R.string.retry),
                onClick = { onRetry.invoke() },
            )
        }

    }

}

@Composable
fun CustomButton(
    buttonColor: Color,
    textColor: Color = Color.White,
    textSize: TextUnit = 12.sp,
    height:Dp = 55.dp,
    shape: Shape,
    buttonText: String,
    textModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    buttonElevation: Dp = 1.dp,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .height(height),
        onClick = { onClick.invoke() },
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f)),
        elevation = ButtonDefaults.buttonElevation(buttonElevation)
    ) {
        Row() {
            Text(
                text = "Retry",
                style = MaterialTheme.typography.displayMedium,
                color = PrimaryBlack
            )
        }
    }
}
















@Composable
@Preview
fun ErrorScreenPreview(){
    ErrorScreen(
        message = "No internet Connection",
        onRetry = {}
    )
}