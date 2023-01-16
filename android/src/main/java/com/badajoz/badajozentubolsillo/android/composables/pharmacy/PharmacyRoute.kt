package com.badajoz.badajozentubolsillo.android.composables.pharmacy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.pharmacy.components.PharmacyContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.pharmacy.PharmacyState
import com.badajoz.badajozentubolsillo.viewmodel.pharmacy.PharmacyViewModel

@Composable
fun PharmacyRoute(onNavigate: (Destination) -> Unit) {
    val viewModel = remember { PharmacyViewModel(initialState = PharmacyState.InProgress) }

    PharmacyContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}



