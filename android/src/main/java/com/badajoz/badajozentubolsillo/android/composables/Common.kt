package com.badajoz.badajozentubolsillo.android.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun LoadingView(background: Color = MaterialTheme.colors.background) {
    Box(
        modifier = Modifier
            .background(color = background)
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center),
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun TopBar(title: String, icon: ImageVector, onNavClick: () -> Unit) {
    TopAppBar(
        // Provide Title
        title = {
            Text(text = title)
        },
        // Provide the navigation Icon (Icon on the left to toggle drawer)
        navigationIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Menu",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier
                    .clickable { onNavClick() }
                    .padding(start = 8.dp)
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}