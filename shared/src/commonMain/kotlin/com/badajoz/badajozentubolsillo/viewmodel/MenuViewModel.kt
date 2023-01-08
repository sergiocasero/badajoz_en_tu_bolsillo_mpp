package com.badajoz.badajozentubolsillo.viewmodel

class MenuViewModel(initialState: MenuState) :
    RootViewModel<MenuState, MenuEvent>(initialState) {

    override fun attach(): MenuViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: MenuEvent) {
        when (event) {
            MenuEvent.Attach -> attach()
            MenuEvent.OnBikeClick -> TODO()
            MenuEvent.OnBusClick -> TODO()
            MenuEvent.OnCalendarClick -> TODO()
            MenuEvent.OnFmdClick -> TODO()
            MenuEvent.OnMinitsClick -> TODO()
            MenuEvent.OnNewsClick -> _uiState.value = MenuState.News
            MenuEvent.OnPharmacyClick -> TODO()
            MenuEvent.OnTaxesClick -> TODO()
        }
    }
}

sealed class MenuState : ViewState() {
    object News : MenuState()
    object Calendar : MenuState()
    object Taxes : MenuState()
    object Fmd : MenuState()
    object Bike : MenuState()
    object Bus : MenuState()
    object Minits : MenuState()
    object Pharmacy : MenuState()
}

sealed class MenuEvent {
    object Attach : MenuEvent()

    object OnNewsClick : MenuEvent()
    object OnCalendarClick : MenuEvent()
    object OnTaxesClick : MenuEvent()
    object OnFmdClick : MenuEvent()
    object OnBikeClick : MenuEvent()
    object OnBusClick : MenuEvent()
    object OnMinitsClick : MenuEvent()
    object OnPharmacyClick : MenuEvent()

}