package com.badajoz.badajozentubolsillo.viewmodel

sealed class Screen(private val destinations: List<Screen>) {
    abstract val route: String
    fun canNavigate(destination: Screen, f: () -> Unit) {
        if (destinations.contains(destination)) {
            f()
        } else {
            throw IllegalArgumentException("Cannot navigate from $this to $destination")
        }
    }

    object News : Screen(listOf(Calendar, Taxes, Fmd, Bike, Bus, Minits, Pharmacy)) {
        override val route: String = "news"
    }

    object Calendar : Screen(listOf(News, Taxes, Fmd, Bike, Bus, Minits, Pharmacy)) {
        override val route: String = "calendar"
    }

    object Taxes : Screen(listOf(Calendar, News, Fmd, Bike, Bus, Minits, Pharmacy)) {
        override val route: String = "taxes"
    }

    object Fmd : Screen(listOf(News, Calendar, Taxes, Bike, Bus, Minits, Pharmacy)) {
        override val route: String = "fmd"
    }

    object Bike : Screen(listOf(News, Calendar, Taxes, Fmd, Bus, Minits, Pharmacy, NewsDetail())) {
        override val route: String = "bike"
    }

    object Bus : Screen(listOf(News, Calendar, Taxes, Fmd, Bike, Minits, Pharmacy, BusLineDetail())) {
        override val route: String = "bus"
    }

    object Minits : Screen(listOf(News, Calendar, Taxes, Fmd, Bike, Bus, Pharmacy)) {
        override val route: String = "minits"
    }

    object Pharmacy : Screen(listOf(News, Calendar, Taxes, Fmd, Bike, Bus, Minits)) {
        override val route: String = "pharmacy"
    }

    class NewsDetail(id: String = "{id}") : Screen(listOf()) {
        override val route: String = "news/$id"
    }

    class BusLineDetail(val id: Int = -1) : Screen(listOf()) {
        override val route: String
            get() = if (id == -1) "bus/{id}" else "bus/$id"
    }

    class ExternalLink(link: String = "") : Screen(listOf()) {
        override val route: String = "external/$link"
    }

    class MapLink(address: String) : Screen(listOf()) {
        override val route: String = "map/$address"
    }
}