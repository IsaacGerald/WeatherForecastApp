package com.senri.weatherforecastapp.presentation.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.touchlab.kampkit.echama.R
import com.touchlab.kampkit.ui.onboarding.components.ChamaButton
import com.touchlab.kampkit.ui.onboarding.components.ChamaCustomButton
import com.touchlab.kampkit.ui.theme.BlueButton
import com.touchlab.kampkit.ui.theme.ChamaBlue
import com.touchlab.kampkit.ui.theme.MidBlue
import com.touchlab.kampkit.ui.theme.regularBoldTextStyle
import com.touchlab.kampkit.ui.theme.regularSemiBoldTextStyle

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
            text = "Oops! Something went wrong",
            style = regularBoldTextStyle.copy(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                color = MidBlue,
                textAlign = TextAlign.Center
            )
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
            text = message,
            style = regularBoldTextStyle.copy(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ChamaCustomButton(
                height = 35.dp,
                buttonColor = BlueButton,
                shape = RoundedCornerShape(40.dp),
                buttonText = stringResource(R.string.retry),
                onClick = { onRetry.invoke() },
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