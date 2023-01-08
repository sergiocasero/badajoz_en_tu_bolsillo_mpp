package com.badajoz.badajozentubolsillo.android.composables.taxes


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModel
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelEvent
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelState

@Composable
fun TaxesRoute() {
    val viewModel = remember { TaxesViewModel(initialState = TaxesViewModelState.InProgress) }

    TaxesContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun TaxesContent(state: TaxesViewModelState, onEvent: (TaxesViewModelEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(TaxesViewModelEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is TaxesViewModelState.InProgress -> LoadingView()
                is TaxesViewModelState.Error -> TODO()
                is TaxesViewModelState.Success -> TaxesSuccess(state.taxes)
            }
        }
    )
}

@Composable
fun TaxesSuccess(taxes: List<TaxGroup>) {
    LazyColumn {
        items(taxes) { tax ->
            Text(text = tax.header)
        }
    }
}
