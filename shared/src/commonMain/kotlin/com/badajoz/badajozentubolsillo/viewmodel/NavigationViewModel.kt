package com.badajoz.badajozentubolsillo.viewmodel

import kotlinx.coroutines.launch

class NavigationViewModel :
    RootViewModel<NavigationState, NavigationEvent, NavigationAction>(NavigationState.Menu) {

    override fun attach(): NavigationViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: NavigationEvent) {
        when (event) {
            NavigationEvent.OnMenu -> _uiState.value = NavigationState.Menu
            is NavigationEvent.OnNewsDetail -> _uiState.value = NavigationState.NewsDetail(event.link)
            NavigationEvent.OnBack -> vmScope.launch { _actions.send(NavigationAction.Back) }
        }
    }
}

sealed class NavigationState : ViewState() {
    object Menu : NavigationState()
    data class NewsDetail(val link: String) : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    object OnMenu : NavigationEvent("menu")
    data class OnNewsDetail(val link: String = "") : NavigationEvent("news_detail/{link}") {
        fun createRoute() = "news_detail/$link"
    }

    object OnBack : NavigationEvent("back")
}

sealed class NavigationAction {
    object Dummy : NavigationAction()
    object Back : NavigationAction()
}