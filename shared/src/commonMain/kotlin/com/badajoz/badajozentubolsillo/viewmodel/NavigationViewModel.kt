package com.badajoz.badajozentubolsillo.viewmodel

class NavigationViewModel :
    RootViewModel<NavigationState, NavigationEvent, NavigationAction>(NavigationState.Home) {

    override fun attach(): NavigationViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: NavigationEvent) = when (event) {
        NavigationEvent.Home -> _uiState.value = NavigationState.Home
    }
}

sealed class NavigationState : ViewState() {
    object Home : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    object Home : NavigationEvent("home")
    /* data class Detail(val id: Long = 0) : NavigationEvent("detail/{poiId}") {
        fun createRoute() = "detail/$id"
    } */
}

sealed class NavigationAction