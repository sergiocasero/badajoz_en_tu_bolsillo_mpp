package com.badajoz.badajozentubolsillo.viewmodel

class NavigationViewModel(initialState: NavigationState) :
    RootViewModel<NavigationState, NavigationEvent>(initialState) {

    override fun attach(): NavigationViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: NavigationEvent) {
        println("Navigation received: $event")
        when (event) {
            is NavigationEvent.OnMenu -> _uiState.value = event.menuState
            is NavigationEvent.OnNewsDetail -> _uiState.value = NavigationState.NewsDetail(event.link)
            is NavigationEvent.OnOpenExternalLink -> _uiState.value = NavigationState.ExternalLink(event.link)
            is NavigationEvent.OnBusLineDetail -> _uiState.value = NavigationState.BusLineDetail(event.lineId)
            is NavigationEvent.OnOpenMapLink -> _uiState.value = NavigationState.MapLink(event.address)
            is NavigationEvent.OnBack -> _uiState.value = event.to
        }
    }
}

sealed class NavigationState : ViewState() {
    data class NewsDetail(val link: String) : NavigationState()
    data class BusLineDetail(val lineId: Int) : NavigationState()
    data class ExternalLink(val link: String) : NavigationState()
    data class MapLink(val address: String) : NavigationState()
}

sealed class NavigationEvent(val route: String) {
    data class OnMenu(val menuState: MenuState) : NavigationEvent("menu/{menuState}") {
        fun createRoute() = when (menuState) {
            MenuState.News -> "menu/news"
            MenuState.Calendar -> "menu/calendar"
            MenuState.Taxes -> "menu/taxes"
            MenuState.Fmd -> "menu/fmd"
            MenuState.Bike -> "menu/bike"
            MenuState.Bus -> "menu/bus"
            MenuState.Minits -> "menu/minits"
            MenuState.Pharmacy -> "menu/pharmacy"
        }
    }

    data class OnNewsDetail(val link: String = "") : NavigationEvent("news_detail/{link}") {
        fun createRoute() = "news_detail/$link"
    }

    data class OnBack(val to: MenuState) : NavigationEvent("back")

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