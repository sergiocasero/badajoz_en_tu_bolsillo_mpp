package com.badajoz.badajozentubolsillo.android.composables.fmd.components.sportdetail

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.reusable.TopBar
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.FmdSportDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.FmdSportDetailState
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import java.net.URLEncoder

@Composable
fun FmdSportDetailContent(
    centerId: Int,
    state: FmdSportDetailState,
    onEvent: (FmdSportDetailEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(FmdSportDetailEvent.Attach)
    }

    Scaffold(
        topBar = {
            if (state is FmdSportDetailState.Success) {
                TopBar(title = "Horarios disponibles", icon = Icons.Default.ArrowBack) {
                    onNavigate(Screen.FmdCenterDetail.toDestination(centerId)) // TODO("This should navigate back")
                }
            }
        },
        floatingActionButton = {
            if (state is FmdSportDetailState.Success) {
                ExtendedFloatingActionButton(
                    text = { Text("Reservar en la FMD") },
                    onClick = {
                        onNavigate(
                            Screen.ExternalLink.toDestination(URLEncoder.encode("https://badajoz.i2a.es", "UTF-8"))
                        )
                    })
            }
        },
        content = {
            when (state) {
                is FmdSportDetailState.InProgress -> LoadingView(animationResource = R.raw.fmd_loading)
                is FmdSportDetailState.Error -> ErrorView(error = state.error) { onEvent(FmdSportDetailEvent.Attach) }
                is FmdSportDetailState.Success -> FmdSportDetailSuccess(state.sport)
            }
        }
    )
}