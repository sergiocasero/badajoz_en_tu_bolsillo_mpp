package com.badajoz.badajozentubolsillo.android.composables.taxes


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.taxes.components.TaxesContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModel
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelState

@Composable
fun TaxesRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { TaxesViewModel(initialState = TaxesViewModelState.InProgress) }

    TaxesContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}







