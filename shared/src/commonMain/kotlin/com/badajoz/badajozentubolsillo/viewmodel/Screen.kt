package com.badajoz.badajozentubolsillo.viewmodel

sealed class Screen {
    abstract val route: String

    abstract val destinations: List<String>

    open val template: String
        get() = route

    fun checkAccess(destination: Screen): Boolean {
        val canNavigate = this.destinations.contains(destination.template)
        println("Can navigate from $route to ${destination.template}? $canNavigate")
        return canNavigate
    }

    object News : Screen() {
        override val route: String = "news"
        override val destinations: List<String> =
            listOf(
                Calendar.template,
                Taxes.template,
                Fmd.template,
                Bike.template,
                Bus.template,
                Minits.template,
                Pharmacy.template,
                NewsDetail().template
            )

        override fun toString(): String = "News"
    }

    object Calendar : Screen() {
        override val route: String = "calendar"
        override val destinations: List<String> =
            listOf(
                News.template,
                Taxes.template,
                Fmd.template,
                Bike.template,
                Bus.template,
                Minits.template,
                Pharmacy.template
            )

        override fun toString(): String = "Calendar"
    }


    object Taxes : Screen() {
        override val route: String = "taxes"
        override val destinations: List<String> =
            listOf(
                News.template,
                Calendar.template,
                Fmd.template,
                Bike.template,
                Bus.template,
                Minits.template,
                Pharmacy.template
            )

        override fun toString(): String = "Taxes"
    }

    object Fmd : Screen() {
        override val route: String = "fmd"
        override val destinations: List<String> =
            listOf(
                News.template,
                Calendar.template,
                Taxes.template,
                Bike.template,
                Bus.template,
                Minits.template,
                Pharmacy.template,
                FmdCenterDetail().template
            )

        override fun toString(): String = "Fmd"
    }

    object Bike : Screen() {
        override val route: String = "bike"
        override val destinations: List<String> =
            listOf(
                News.template,
                Calendar.template,
                Taxes.template,
                Fmd.template,
                Bus.template,
                Minits.template,
                Pharmacy.template
            )

        override fun toString(): String = "Bike"
    }

    object Bus : Screen() {
        override val route: String = "bus"
        override val destinations: List<String> =
            listOf(
                News.template,
                Calendar.template,
                Taxes.template,
                Fmd.template,
                Bike.template,
                Minits.template,
                Pharmacy.template,
                BusLineDetail().template
            )

        override fun toString(): String = "Bus"
    }

    object Minits : Screen() {
        override val route: String = "minits"
        override val destinations: List<String> =
            listOf(
                News.template,
                Calendar.template,
                Taxes.template,
                Fmd.template,
                Bike.template,
                Bus.template,
                Pharmacy.template
            )

        override fun toString(): String = "Minits"
    }

    object Pharmacy : Screen() {
        override val route: String = "pharmacy"
        override val destinations: List<String> =
            listOf(
                News.template,
                Calendar.template,
                Taxes.template,
                Fmd.template,
                Bike.template,
                Bus.template,
                Minits.template
            )

        override fun toString(): String = "Pharmacy"
    }

    class NewsDetail(val link: String = "{link}") : Screen() {
        override val route: String = "news/$link"
        override val destinations: List<String> = listOf(News.template, ExternalLink().template)
        override val template: String = "news/{link}"
        override fun toString(): String = "NewsDetail($route)"
    }

    class BusLineDetail(val id: Int = -1) : Screen() {
        override val route: String = "bus/$id"
        override val destinations: List<String> = listOf(Bus.template, MapLink().template)
        override val template: String = "bus/{id}"
        override fun toString(): String = "BusLineDetail($route)"
    }

    class FmdCenterDetail(val id: Int = -1) : Screen() {
        override val route: String = "fmd/$id"
        override val destinations: List<String> = listOf(Fmd.template, MapLink().template)
        override val template: String = "fmd/{id}"
        override fun toString(): String = "FmdCenterDetail($route)"
    }

    class ExternalLink(link: String = "") : Screen() {
        override val route: String = "external/$link"
        override val destinations: List<String> = listOf()
        override val template: String = "external/{link}"
        override fun toString(): String = "ExternalLink($route)"
    }

    class MapLink(address: String = "") : Screen() {
        override val route: String = "map/$address"
        override val destinations: List<String> = listOf()
        override val template: String = "map/{address}"
        override fun toString(): String = "MapLink($route)"
    }
}