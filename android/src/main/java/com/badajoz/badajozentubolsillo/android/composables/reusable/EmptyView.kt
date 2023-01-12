package com.badajoz.badajozentubolsillo.android.composables.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R

@Composable
fun EmptyView(
    message: String,
    icon: ImageVector,
    textColor: Color = MaterialTheme.colors.onBackground,
    background: Color = MaterialTheme.colors.background,
    buttonText: String? = null,
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.empty),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp),
                tint = textColor
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                color = textColor
            )
            if (buttonText != null) {
                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    onClick = onButtonClick
                ) {
                    Text(text = buttonText)
                }
            }

        }
    }
}

@Preview
@Composable
fun EmptyViewPreview() {
    EmptyView(
        message = "Aún no tienes paradas favoritas, añádelas en el listado de paradas dentro de cada línea",
        icon = Icons.Filled.FavoriteBorder,
        background = Color.White
    )
}