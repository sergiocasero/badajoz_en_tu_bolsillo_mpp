package com.badajoz.badajozentubolsillo.android.composables.pharmacy


import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyGroup
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyEvent
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyState
import com.badajoz.badajozentubolsillo.viewmodel.PharmacyViewModel

@Composable
fun PharmacyRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { PharmacyViewModel(initialState = PharmacyState.InProgress) }

    PharmacyContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun PharmacyContent(state: PharmacyState, onEvent: (PharmacyEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(PharmacyEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is PharmacyState.InProgress -> LoadingView()
                is PharmacyState.Error -> TODO()
                is PharmacyState.Success -> PharmacySuccessView(state.pharmacy)
            }
        }
    )
}

@Composable
fun PharmacySuccessView(pharmacy: List<PharmacyGroup>) {
    Text(text = pharmacy.first().title)
}
