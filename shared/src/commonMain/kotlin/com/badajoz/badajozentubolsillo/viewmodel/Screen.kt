package com.badajoz.badajozentubolsillo.viewmodel

data class Destination(val template: String, val to: String)
sealed class Screen {
    // abstract fun build, it could receive N parameters
    open fun toDestination(vararg params: Any): Destination = Destination(template = route, to = route)

    protected abstract val destinations: List<String>

    abstract val route: String

    fun checkAccess(destination: Destination): Boolean {
        val canNavigate = this.destinations.contains(destination.template)
        println("Can navigate from $route to ${destination.to}? $canNavigate")
        return canNavigate
    }

    object News : Screen() {
        override val route
            get() = "news"
        override val destinations: List<String> =
            listOf(
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Bus.route,
                Minits.route,
                Pharmacy.route,
                NewsDetail.route
            )

        override fun toString(): String = "News"
    }

    object Calendar : Screen() {
        override val route
            get() = "calendar"
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
        override val route
            get() = "taxes"
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
        override val route
            get() = "fmd"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Bike.route,
                Bus.route,
                Minits.route,
                Pharmacy.route,
                FmdCenterDetail.route
            )

        override fun toString(): String = "Fmd"
    }

    object Bike : Screen() {
        override val route
            get() = "bike"
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
        override val route
            get() = "bus"
        override val destinations: List<String> =
            listOf(
                News.route,
                Calendar.route,
                Taxes.route,
                Fmd.route,
                Bike.route,
                Minits.route,
                Pharmacy.route,
                BusLineDetail.route
            )

        override fun toString(): String = "Bus"
    }

    object Minits : Screen() {
        override val route
            get() = "minits"
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
        override val route
            get() = "pharmacy"
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

    object NewsDetail : Screen() {
        override val route
            get() = "news/{link}"
        override val destinations: List<String> = listOf(News.route, ExternalLink.route)
        override fun toDestination(vararg params: Any): Destination =
            Destination(
                route,
                if (params.size == 1) {
                    val link = params[0] as String
                    "news/$link"
                } else {
                    route
                }
            )

        override fun toString(): String = "NewsDetail($route)"
    }

    object BusLineDetail : Screen() {
        override val destinations: List<String> = listOf(Bus.route, MapLink.route)
        override val route
            get() = "bus/{id}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                route,
                if (params.size == 1) {
                    val id = params[0] as Int
                    "bus/$id"
                } else {
                    route
                }
            )

        override fun toString(): String = "BusLineDetail($route)"
    }

    object FmdCenterDetail : Screen() {
        override val destinations: List<String> = listOf(Fmd.route, FmdSportDetail.route, MapLink.route)
        override val route
            get() = "fmd/{id}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                route,
                if (params.size == 1) {
                    val id = params[0] as Int
                    "fmd/$id"
                } else {
                    route
                }
            )

        override fun toString(): String = "FmdCenterDetail($route)"
    }

    object FmdSportDetail : Screen() {
        override val destinations: List<String> = listOf(MapLink.route, FmdCenterDetail.route)
        override val route
            get() = "fmd/{centerId}/{sportId}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                route,
                if (params.size == 2) {
                    val centerId = params[0] as Int
                    val sportId = params[1] as Int
                    "fmd/$centerId/$sportId"
                } else {
                    route
                }
            )

        override fun toString(): String = "FmdSportDetail($route)"
    }

    object ExternalLink : Screen() {
        override val destinations: List<String> = listOf()
        override val route
            get() = "external/{link}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                route,
                if (params.size == 1) {
                    val link = params[0] as String
                    "external/$link"
                } else {
                    route
                }
            )

        override fun toString(): String = "ExternalLink($route)"
    }

    object MapLink : Screen() {
        override val destinations: List<String> = listOf()
        override val route
            get() = "map/{address}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                route,
                if (params.size == 1) {
                    val address = params[0] as String
                    "map/$address"
                } else {
                    route
                }
            )

        override fun toString(): String = "MapLink($route)"
    }
}