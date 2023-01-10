package com.badajoz.badajozentubolsillo.android.composables.pharmacy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.pharmacy.components.PharmacyContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyState
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyViewModel

@Composable
fun PharmacyRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { PharmacyViewModel(initialState = PharmacyState.InProgress) }

    PharmacyContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}



