package com.badajoz.badajozentubolsillo.android.composables.reusable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.SyncProblem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.model.AppError

@Composable
fun ErrorView(error: AppError, onRetry: () -> Unit) {
    val message = when (error) {
        AppError.LocalError -> R.string.local_error
        AppError.NotFound -> R.string.not_found
        AppError.Unknown -> R.string.unknown
        AppError.NoInternet -> R.string.no_internet
        AppError.ServerError -> R.string.server_error
        AppError.AppConfig -> R.string.app_config
    }

    val icon = when (error) {
        AppError.LocalError -> Icons.Filled.Dangerous
        AppError.NotFound -> Icons.Filled.Dangerous
        AppError.Unknown -> Icons.Filled.Dangerous
        AppError.NoInternet -> Icons.Filled.SyncProblem
        AppError.ServerError -> Icons.Filled.CloudOff
        AppError.AppConfig -> Icons.Filled.Error
    }
    EmptyView(
        message = stringResource(id = message),
        icon = icon,
        textColor = MaterialTheme.colors.onError,
        background = MaterialTheme.colors.error,
        buttonText = stringResource(id = R.string.retry),
        onButtonClick = onRetry
    )
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(error = AppError.LocalError, onRetry = {})
}