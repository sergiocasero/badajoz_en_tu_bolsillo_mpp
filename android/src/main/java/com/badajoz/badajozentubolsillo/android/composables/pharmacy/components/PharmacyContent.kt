package com.badajoz.badajozentubolsillo.android.composables.pharmacy.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyEvent
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyState

@Composable
fun PharmacyContent(
    state: PharmacyState,
    onEvent: (PharmacyEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(PharmacyEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is PharmacyState.InProgress -> LoadingView()
                is PharmacyState.Error -> ErrorView(error = state.error) { onEvent(PharmacyEvent.Attach) }
                is PharmacyState.Success -> PharmacySuccessView(state.pharmacy) {
                    onNavigationEvent(NavigationEvent.OnOpenMapLink(it.address))
                }
            }
        }
    )
}