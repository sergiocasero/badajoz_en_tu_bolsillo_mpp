package com.badajoz.badajozentubolsillo.viewmodel

class NavigationGraph {
    fun checkPermission(
        from: Screen,
        to: Screen,
        route: String? = null,
        onBack: () -> Unit = {},
        onGranted: (String) -> Unit = {},
        onLink: (String) -> Unit = {},
        onMap: (String) -> Unit = {}
    ) {
        if (from == to) {
            throw IllegalArgumentException("You are trying to navigate to the same screen")
        }
        when (from) {
            Screen.BusLineDetail -> when (to) {
                Screen.Menu.Bus -> onBack()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }

            Screen.ExternalLink -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            Screen.FmdCenterDetail -> when (to) {
                Screen.FmdSportDetail -> when (route) {
                    null -> throw IllegalArgumentException("This screen needs centerId and stopId to be defined")
                    else -> onGranted(route)
                }

                Screen.Menu.Fmd -> onBack()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }

            Screen.FmdSportDetail -> when (to) {
                Screen.FmdCenterDetail -> onBack()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }

            Screen.MapLink -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            is Screen.Menu -> {
                when {
                    to is Screen.Menu -> onGranted(to.route)
                    (from is Screen.Menu.Pharmacy || from is Screen.Menu.Bike || from is Screen.Menu.Fmd)
                            && to is Screen.MapLink -> onMap(to.route)

                    from is Screen.Menu.Taxes
                            && to is Screen.ExternalLink
                            && route != null -> onLink(route)

                    from is Screen.Menu.News
                            && to is Screen.NewsDetail
                            && route != null -> onGranted(route)

                    from is Screen.Menu.Bus
                            && to is Screen.BusLineDetail
                            && route != null -> onGranted(route)
                }
            }

            Screen.NewsDetail -> when (to) {
                Screen.Menu.News -> onBack()
                else -> throw IllegalArgumentException("You are trying to navigate to a screen that is not allowed")
            }
        }
    }
}

data class Destination(val screen: Screen, val route: String? = null)
sealed class Screen {
    // abstract fun build, it could receive N parameters
    open fun toDestination(vararg params: Any): Destination = Destination(screen = this, route = route)
    abstract val route: String

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

        override fun toDestination(vararg params: Any): Destination =
            Destination(
                this,
                if (params.size == 1) {
                    val id = params[0] as String
                    "news/$id"
                } else {
                    route
                }
            )

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
                    link
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
                    address
                } else {
                    route
                }
            )

        override fun toString(): String = "MapLink($route)"
    }
}