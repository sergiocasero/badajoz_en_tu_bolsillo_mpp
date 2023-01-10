package com.badajoz.badajozentubolsillo.viewmodel

class MenuViewModel(initialState: MenuState) :
    RootViewModel<MenuState, MenuEvent>(initialState) {

    override fun attach(): MenuViewModel = apply {
        // do nothing
    }

    override fun onEvent(event: MenuEvent) {
        when (event) {
            MenuEvent.Attach -> attach()
        }
    }
}

sealed class MenuState : NavigationState() {
    companion object {
        fun values() = listOf(
            News,
            Calendar,
            Taxes,
            Fmd,
            Bike,
            Bus,
            // Minits,
            Pharmacy
        )
    }

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
}