package com.badajoz.badajozentubolsillo.android.composables.reusable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.utils.MaterialColor


val defaultCardElevation = 0.dp
val defaultCardBorderColor = Color(MaterialColor.GREY.tone(500, 50))
val defaultCardCornerShape = RoundedCornerShape(4.dp)

@Composable
fun DefaultCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .border(1.dp, defaultCardBorderColor, defaultCardCornerShape),
        elevation = defaultCardElevation
    ) {
        content()
    }
}