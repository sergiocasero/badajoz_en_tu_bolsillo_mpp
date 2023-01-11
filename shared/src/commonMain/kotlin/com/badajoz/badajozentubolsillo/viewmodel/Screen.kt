package com.badajoz.badajozentubolsillo.viewmodel

sealed class Screen {
    abstract val route: String

    abstract val destinations: List<String>
    fun checkAccess(destination: Screen): Boolean {
        println(this.destinations)
        val result = this.destinations.contains(destination.route)
        println("Route from $this to $destination is $result")
        return result
    }

    object News : Screen() {
        override val route: String = "news"
        override val destinations: List<String> =
            listOf(
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Bus.route,
                Minits.route,
                Pharmacy.route,
                NewsDetail().route
            )

        override fun toString(): String = "News"
    }

    object Calendar : Screen() {
        override val route: String = "calendar"
        override val destinations: List<String> =
            listOf(
                News.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Bus.route,
                Minits.route,
                Pharmacy.route
            )

        override fun toString(): String = "Calendar"
    }


    object Taxes : Screen() {
        override val route: String = "taxes"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Fmd.route,
                Bike.route,
                Bus.route,
                Minits.route,
                Pharmacy.route
            )

        override fun toString(): String = "Taxes"
    }

    object Fmd : Screen() {
        override val route: String = "fmd"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Bike.route,
                Bus.route,
                Minits.route,
                Pharmacy.route
            )

        override fun toString(): String = "Fmd"
    }

    object Bike : Screen() {
        override val route: String = "bike"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bus.route,
                Minits.route,
                Pharmacy.route
            )

        override fun toString(): String = "Bike"
    }

    object Bus : Screen() {
        override val route: String = "bus"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Minits.route,
                Pharmacy.route,
                BusLineDetail().route
            )

        override fun toString(): String = "Bus"
    }

    object Minits : Screen() {
        override val route: String = "minits"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Bus.route,
                Pharmacy.route
            )

        override fun toString(): String = "Minits"
    }

    object Pharmacy : Screen() {
        override val route: String = "pharmacy"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Bus.route,
                Minits.route
            )

        override fun toString(): String = "Pharmacy"
    }

    class NewsDetail(id: String = "{id}") : Screen() {
        override val route: String = "news/$id"
        override val destinations: List<String> = listOf(ExternalLink().route)
        override fun toString(): String = "NewsDetail($route)"
    }

    class BusLineDetail(val id: Int = -1) : Screen() {
        override val route: String
            get() = if (id == -1) "bus/{id}" else "bus/$id"
        override val destinations: List<String> = listOf(MapLink().route)

        override fun toString(): String = "BusLineDetail($route)"
    }

    class FmdCenterDetail(val id: Int = -1) : Screen() {
        override val route: String
            get() = if (id == -1) "fmd/{id}" else "fmd/$id"
        override val destinations: List<String> = listOf(MapLink().route)

        override fun toString(): String = "FmdCenterDetail($route)"
    }

    class ExternalLink(link: String = "") : Screen() {
        override val route: String = "external/$link"
        override val destinations: List<String> = listOf()
        override fun toString(): String = "ExternalLink($route)"
    }

    class MapLink(address: String = "") : Screen() {
        override val route: String = "map/$address"
        override val destinations: List<String> = listOf()
        override fun toString(): String = "MapLink($route)"
    }
}