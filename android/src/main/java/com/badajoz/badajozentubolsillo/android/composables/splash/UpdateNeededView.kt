package com.badajoz.badajozentubolsillo.android.composables.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.reusable.EmptyView
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun UpdateNeededView(onNavigate: (Destination) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        EmptyView(
            message = stringResource(id = R.string.update_needed),
            icon = Icons.Default.Update,
            buttonText = stringResource(id = R.string.update_app),
            onButtonClick = {
                onNavigate(
                    Screen.ExternalLink.toDestination(
                        "https://play.google.com/store/apps/details?id=com.badajoz.badajozentubolsillo"
                    )
                )
            }
        )
    }
}