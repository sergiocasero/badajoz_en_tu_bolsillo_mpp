package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerdetail

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.reusable.TopBar
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.fmd.FmdCenterDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.fmd.FmdCenterDetailState
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCenterDetailContent(
    state: FmdCenterDetailState,
    onEvent: (FmdCenterDetailEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(FmdCenterDetailEvent.Attach)
    }

    Scaffold(
        topBar = {
            if (state is FmdCenterDetailState.Success) {
                TopBar(title = state.center.title, icon = Icons.Default.ArrowBack) {
                    onNavigate(Screen.Menu.Fmd.toDestination())
                }
            }
        },
        content = {
            when (state) {
                is FmdCenterDetailState.InProgress -> LoadingView()
                is FmdCenterDetailState.Error -> ErrorView(error = state.error) { onEvent(FmdCenterDetailEvent.Attach) }
                is FmdCenterDetailState.Success -> FmdSportsListView(state.appConfigData, state.center.sports,
                    onNavigate)
            }
        }
    )
}