package com.badajoz.badajozentubolsillo.android.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextBox(label: String, color: Color) {
    Box(
        modifier = Modifier
            .width(60.dp)
            .background(color)
            .fillMaxHeight()
    ) {
        Text(
            text = label,
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}