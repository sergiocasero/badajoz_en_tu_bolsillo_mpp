package com.badajoz.badajozentubolsillo.android.composables.fmd


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.fmd.components.sportdetail.FmdSportDetailContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.FmdSportDetailState
import com.badajoz.badajozentubolsillo.viewmodel.FmdSportDetailViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdSportDetailRoute(centerId: Int, sportId: Int, onNavigate: (Destination) -> Unit) {
    val viewModel = remember {
        FmdSportDetailViewModel(
            centerId = centerId,
            sportId = sportId,
            initialState = FmdSportDetailState.InProgress
        )
    }

    FmdSportDetailContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}
