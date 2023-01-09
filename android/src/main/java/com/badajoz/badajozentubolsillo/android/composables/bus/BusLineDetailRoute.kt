package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailState
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailViewModel

@Composable
fun BusLineDetailRoute(line: BusLine) {
    val viewModel = remember { BusLineDetailViewModel(line = line, initialState = BusLineDetailState.InProgress) }

    BusLineDetailContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun BusLineDetailContent(state: BusLineDetailState, onEvent: (BusLineDetailEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(BusLineDetailEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is BusLineDetailState.InProgress -> LoadingView()
                is BusLineDetailState.Error -> TODO()
                is BusLineDetailState.Success -> BusLineDetailView(state.line, state.stops, state.bigImage)
            }
        }
    )
}

@Composable
fun BusLineDetailView(line: BusLine, stops: List<BusStop>, bigImage: Boolean) {
    Scaffold {
        Text(line.description)
    }
}
