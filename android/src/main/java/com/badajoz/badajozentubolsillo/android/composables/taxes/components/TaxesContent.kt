package com.badajoz.badajozentubolsillo.android.composables.taxes.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelEvent
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelState

@Composable
fun TaxesContent(
    state: TaxesViewModelState,
    onEvent: (TaxesViewModelEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(TaxesViewModelEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is TaxesViewModelState.InProgress -> LoadingView()
                is TaxesViewModelState.Error -> ErrorView(error = state.error) { onEvent(TaxesViewModelEvent.Attach) }
                is TaxesViewModelState.Success -> TaxesSuccess(state.taxes) {
                    onNavigate(Screen.ExternalLink.toDestination(it.url))
                }
            }
        }
    )
}