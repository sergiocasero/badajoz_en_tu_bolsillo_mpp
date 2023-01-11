package com.badajoz.badajozentubolsillo.android.composables.fmd.components.sportdetail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.FmdSportDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.FmdSportDetailState

@Composable
fun FmdSportDetailContent(
    state: FmdSportDetailState,
    onEvent: (FmdSportDetailEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(FmdSportDetailEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is FmdSportDetailState.InProgress -> LoadingView(animationResource = R.raw.fmd_loading)
                is FmdSportDetailState.Error -> ErrorView(error = state.error) { onEvent(FmdSportDetailEvent.Attach) }
                is FmdSportDetailState.Success -> FmdSportDetailSuccess(state.sport)
            }
        }
    )
}