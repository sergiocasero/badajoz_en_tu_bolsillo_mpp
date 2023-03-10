package com.badajoz.badajozentubolsillo.viewmodel.menu

import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewEvent
import com.badajoz.badajozentubolsillo.viewmodel.ViewState

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

sealed class MenuState : ViewState() {
    companion object {
        fun values() = listOf(
            News,
            Calendar,
            Taxes,
            Fmd,
            Bike,
            Bus,
            // Minits,
            Pharmacy,
            About
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
    object About : MenuState()
}

sealed class MenuEvent : ViewEvent() {
    object Attach : MenuEvent()
}