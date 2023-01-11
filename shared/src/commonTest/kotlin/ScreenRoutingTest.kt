import com.badajoz.badajozentubolsillo.viewmodel.Screen
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Bike
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Bus
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Calendar
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Fmd
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Minits
import com.badajoz.badajozentubolsillo.viewmodel.Screen.News
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Pharmacy
import com.badajoz.badajozentubolsillo.viewmodel.Screen.Taxes
import kotlin.test.Test
import kotlin.test.assertTrue

class ScreenRoutingTest {

    private val menuRoutes = listOf(
        News,
        Calendar,
        Taxes,
        Fmd,
        Bike,
        Bus,
        Minits,
        Pharmacy
    )

    private val allRoutes = menuRoutes + Screen.NewsDetail() + Screen.BusLineDetail() + Screen.FmdCenterDetail()

    @Test
    fun `News menu route could navigate to all other menu routes except itself`() {
        val from = News
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Calendar menu route could navigate to all other menu routes except itself`() {
        val from = Calendar
        val to = menuRoutes.filter { it != from }
        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Taxes menu route could navigate to all other menu routes except itself`() {
        val from = Taxes
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Fmd menu route could navigate to all other menu routes except itself`() {
        val from = Fmd
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Bike menu route could navigate to all other menu routes except itself`() {
        val from = Bike
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Bus menu route could navigate to all other menu routes except itself`() {
        val from = Bus
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Minits menu route could navigate to all other menu routes except itself`() {
        val from = Minits
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Pharmacy menu route could navigate to all other menu routes except itself`() {
        val from = Pharmacy
        val to = menuRoutes.filter { it != from }

        assertTrue(to.all { from.checkAccess(it) })
    }

    @Test
    fun `Only News menu route can navigate to News Detail`() {
        val from = News
        val to = Screen.NewsDetail()

        assertTrue(from.checkAccess(to))

        val otherRoutes = allRoutes.filter { it != from }

        assertTrue(otherRoutes.all { !it.checkAccess(to) })
    }

    @Test
    fun `Only Bus menu route can navigate to Bus Line Detail`() {
        val from = Bus
        val to = Screen.BusLineDetail()

        assertTrue(from.checkAccess(to))

        val otherRoutes = allRoutes.filter { it != from }

        assertTrue(otherRoutes.all { !it.checkAccess(to) })
    }

    @Test
    fun `NewsDetail cannot navigate to any screen except ExternalLink`() {
        val from = Screen.NewsDetail()
        val to = allRoutes.filter { it !is Screen.ExternalLink }

        assertTrue(to.all { !from.checkAccess(it) })

        val externalLink = Screen.ExternalLink()
        assertTrue(from.checkAccess(externalLink))
    }

    @Test
    fun `BusLineDetail cannot navigate to any screen Except MapLink`() {
        val from = Screen.BusLineDetail()
        val to = allRoutes.filter { it !is Screen.MapLink }

        assertTrue(to.all { !from.checkAccess(it) })

        val mapLink = Screen.MapLink()
        assertTrue(from.checkAccess(mapLink))
    }

    @Test
    fun `Fmd route should be able to navigate to FmdCenterDetail`() {
        val from = Fmd
        val to = Screen.FmdCenterDetail()

        assertTrue(from.checkAccess(to))
    }

    @Test
    fun `Fmd center ID cannot navigate to any screen`() {
        val from = Screen.FmdCenterDetail()
        val to = allRoutes.filter { it !is Screen.FmdCenterDetail }

        assertTrue(to.all { !from.checkAccess(it) })
    }
}