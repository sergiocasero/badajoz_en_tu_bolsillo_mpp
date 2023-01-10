package com.badajoz.badajozentubolsillo.android.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.SyncProblem
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.model.AppError

@Composable
fun ErrorView(error: AppError, onRetry: () -> Unit) {
    val message = when (error) {
        AppError.LocalError -> "Ha ocurrido un error al guardar la información, inténtalo de nuevo más tarde"
        AppError.NotFound -> "No encontrado"
        AppError.Unknown -> "Ha ocurrido un error inesperado"
        AppError.NoInternet -> "No se ha podido conectar con el servidor, comprueba tu conexión a internet"
        AppError.ServerError -> "Ha ocurrido un error en el servidor, inténtalo de nuevo más tarde"
    }

    val icon = when (error) {
        AppError.LocalError -> Icons.Filled.Dangerous
        AppError.NotFound -> Icons.Filled.Dangerous
        AppError.Unknown -> Icons.Filled.Dangerous
        AppError.NoInternet -> Icons.Filled.SyncProblem
        AppError.ServerError -> Icons.Filled.CloudOff
    }
    EmptyView(
        message = message,
        icon = icon,
        textColor = MaterialTheme.colors.onError,
        background = MaterialTheme.colors.error,
        buttonText = "Reintentar",
        onButtonClick = onRetry
    )
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(error = AppError.LocalError, onRetry = {})
}