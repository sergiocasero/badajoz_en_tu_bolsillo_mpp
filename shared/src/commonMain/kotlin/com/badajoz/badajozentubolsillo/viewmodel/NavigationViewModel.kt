package com.badajoz.badajozentubolsillo.viewmodel

class NavigationViewModel :
    RootViewModel<NavigationState, NavigationEvent, NavigationAction>(NavigationState.Menu) {

    override fun attach(): NavigationViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: NavigationEvent) = when (event) {
        NavigationEvent.Menu -> _uiState.value = NavigationState.Menu
    }
}

sealed class NavigationState : ViewState() {
    object Menu : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    object Menu : NavigationEvent("menu")
    /* data class Detail(val id: Long = 0) : NavigationEvent("detail/{poiId}") {
        fun createRoute() = "detail/$id"
    } */
}

sealed class NavigationAction