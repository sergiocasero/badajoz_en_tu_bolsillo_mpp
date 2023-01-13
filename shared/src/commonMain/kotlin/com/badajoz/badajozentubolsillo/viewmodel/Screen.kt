package com.badajoz.badajozentubolsillo.viewmodel

data class Destination(val screen: Screen, val route: String? = null)
sealed class Screen {
    // abstract fun build, it could receive N parameters
    open fun toDestination(vararg params: Any): Destination = Destination(screen = this, route = route)
    abstract val route: String

    fun checkAccess(destination: Destination, back: () -> Unit = {}, granted: (String) -> Unit = {}) {
        if (this == destination.screen) {
            throw IllegalArgumentException("You are trying to navigate to the same screen")
        }
        when (this) {
            BusLineDetail -> when (destination.screen) {
                Menu.Bus -> back()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }

            ExternalLink -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            FmdCenterDetail -> when (destination.screen) {
                FmdSportDetail -> when (destination.route) {
                    null -> throw IllegalArgumentException("This screen needs centerId and stopId to be defined")
                    else -> granted(destination.route)
                }

                Menu.Fmd -> back()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }

            FmdSportDetail -> when (destination.screen) {
                FmdCenterDetail -> back()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }

            MapLink -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            is Menu -> {
                if (destination.screen is Menu) {
                    granted(destination.screen.route) // Menu routes don't have parameters
                }
            }

            NewsDetail -> when (destination.screen) {
                Menu.News -> back()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }
        }
    }

    sealed class Menu : Screen() {

        companion object {
            fun values() = listOf(News, Calendar, Taxes, About, Fmd, Bike, Bus, Minits, Pharmacy)
        }

        object News : Menu() {
            override val route
                get() = "news"

            override fun toString(): String = "News"
        }

        object Calendar : Menu() {
            override val route
                get() = "calendar"

            override fun toString(): String = "Calendar"
        }


        object Taxes : Menu() {
            override val route
                get() = "taxes"

            override fun toString(): String = "Taxes"
        }

        object About : Menu() {
            override val route
                get() = "about"

            override fun toString(): String = "About"
        }

        object Fmd : Menu() {
            override val route
                get() = "fmd"

            override fun toString(): String = "Fmd"
        }

        object Bike : Menu() {
            override val route
                get() = "bike"

            override fun toString(): String = "Bike"
        }

        object Bus : Menu() {
            override val route
                get() = "bus"

            override fun toString(): String = "Bus"
        }

        object Minits : Menu() {
            override val route
                get() = "minits"

            override fun toString(): String = "Minits"
        }

        object Pharmacy : Menu() {
            override val route
                get() = "pharmacy"

            override fun toString(): String = "Pharmacy"
        }
    }

    object NewsDetail : Screen() {
        override val route
            get() = "news/{link}"

        override fun toString(): String = "NewsDetail($route)"
    }

    object BusLineDetail : Screen() {
        override val route
            get() = "bus/{id}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                this,
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
        override val route
            get() = "fmd/{id}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                this,
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
        override val route
            get() = "fmd/{centerId}/{sportId}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                this,
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
        override val route
            get() = "external/{link}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                this,
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
        override val route
            get() = "map/{address}"

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                this,
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