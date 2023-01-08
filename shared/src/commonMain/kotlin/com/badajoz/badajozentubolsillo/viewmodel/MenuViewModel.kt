package com.badajoz.badajozentubolsillo.viewmodel

class MenuViewModel(initialState: MenuState) :
    RootViewModel<MenuState, MenuEvent>(initialState) {

    override fun attach(): MenuViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: MenuEvent) {
        when (event) {
            MenuEvent.Attach -> attach()
            MenuEvent.OnBikeClick -> _uiState.value = MenuState.Bike
            MenuEvent.OnBusClick -> _uiState.value = MenuState.Bus
            MenuEvent.OnCalendarClick -> _uiState.value = MenuState.Calendar
            MenuEvent.OnFmdClick -> _uiState.value = MenuState.Fmd
            MenuEvent.OnMinitsClick -> _uiState.value = MenuState.Minits
            MenuEvent.OnNewsClick -> _uiState.value = MenuState.News
            MenuEvent.OnPharmacyClick -> _uiState.value = MenuState.Pharmacy
            MenuEvent.OnTaxesClick -> _uiState.value = MenuState.Taxes
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