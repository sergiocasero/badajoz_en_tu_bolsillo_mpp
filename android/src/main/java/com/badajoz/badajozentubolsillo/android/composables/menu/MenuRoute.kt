package com.badajoz.badajozentubolsillo.android.composables.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.menu.components.MenuContent
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.MenuViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen


@Composable
fun MenuRoute(state: MenuState, onNavigate: (Destination) -> Unit) {
    val viewModel = remember { MenuViewModel(state) }

    MenuContent(
        state = state,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}
