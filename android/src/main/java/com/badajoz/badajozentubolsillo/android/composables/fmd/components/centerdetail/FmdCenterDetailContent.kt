package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerdetail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.FmdCenterDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.FmdCenterDetailState
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCenterDetailContent(
    state: FmdCenterDetailState,
    onEvent: (FmdCenterDetailEvent) -> Unit,
    onNavigate: (Screen) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(FmdCenterDetailEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is FmdCenterDetailState.InProgress -> LoadingView()
                is FmdCenterDetailState.Error -> ErrorView(error = state.error) { onEvent(FmdCenterDetailEvent.Attach) }
                is FmdCenterDetailState.Success -> FmdSportsListView(state.center.sports)
            }
        }
    )
}