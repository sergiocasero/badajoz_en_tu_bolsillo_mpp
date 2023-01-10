package com.badajoz.badajozentubolsillo.android.composables.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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