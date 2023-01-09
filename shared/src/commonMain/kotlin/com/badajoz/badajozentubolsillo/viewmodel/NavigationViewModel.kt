package com.badajoz.badajozentubolsillo.viewmodel

class NavigationViewModel :
    RootViewModel<NavigationState, NavigationEvent>(NavigationState.Menu) {

    override fun attach(): NavigationViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: NavigationEvent) {
        when (event) {
            NavigationEvent.OnMenu -> _uiState.value = NavigationState.Menu
            is NavigationEvent.OnNewsDetail -> _uiState.value = NavigationState.NewsDetail(event.link)
            NavigationEvent.OnBack -> _uiState.value = NavigationState.Menu
            is NavigationEvent.OnOpenExternalLink -> _uiState.value = NavigationState.ExternalLink(event.link)
            is NavigationEvent.OnBusLineDetail -> _uiState.value = NavigationState.BusLineDetail(event.lineId)
            is NavigationEvent.OnOpenMapLink -> _uiState.value = NavigationState.MapLink(event.address)
        }
    }
}

sealed class NavigationState : ViewState() {
    object Menu : NavigationState()
    data class NewsDetail(val link: String) : NavigationState()
    data class BusLineDetail(val lineId: Int) : NavigationState()
    data class ExternalLink(val link: String) : NavigationState()
    data class MapLink(val address: String) : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    object OnMenu : NavigationEvent("menu")
    data class OnNewsDetail(val link: String = "") : NavigationEvent("news_detail/{link}") {
        fun createRoute() = "news_detail/$link"
    }

    object OnBack : NavigationEvent("back")

    data class OnOpenExternalLink(val link: String = "") : NavigationEvent("open_external/{linkname}") {
        fun createRoute(link: String) = "open_external/$link"
    }

    data class OnOpenMapLink(val address: String = "") : NavigationEvent("open_map/{address}") {
        fun createRoute(link: String) = "open_map/$link"
    }

    data class OnBusLineDetail(val lineId: Int = 0) : NavigationEvent("bus_line_detail/{lineId}") {
        fun createRoute() = "bus_line_detail/$lineId"
    }
}