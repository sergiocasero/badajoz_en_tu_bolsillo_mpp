package com.badajoz.badajozentubolsillo.android.composables.news

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.model.category.news.NewsPage
import com.badajoz.badajozentubolsillo.viewmodel.HomeEvent
import com.badajoz.badajozentubolsillo.viewmodel.HomeState
import com.badajoz.badajozentubolsillo.viewmodel.HomeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import kotlin.reflect.KFunction1

@Composable
fun NewsRoute(onNavigationEvent: KFunction1<NavigationEvent, Unit>) {
    val viewModel = remember { HomeViewModel(HomeState.InProgress) }

    NewsContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun NewsContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(HomeEvent.Attach)
    }

    Scaffold {
        when (state) {
            is HomeState.Error -> TODO()
            HomeState.InProgress -> CircularProgressIndicator()
            is HomeState.Success -> NewsList(state.page.news)
        }
    }
}

@Composable
fun NewsList(news: List<News>) {
    LazyColumn {
        items(news) {
            NewsItem(news = it)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsItem(news: News) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(4.dp)
            .border(width = 1.dp, color = Color(0xFF3700B3))
            .padding(4.dp)
    ) {
        Text(text = news.title)
    }
}

@Preview
@Composable
fun NewsContentPreview() {
    val news = listOf(
        News(
            title = "Orden del Concurso Oficial de Murgas del Carnaval de Badajoz 2023",
            category = "Ferias y Fiestas / Carnaval",
            date = "07/01/2023",
            link = "https://www.aytobadajoz.es/es/ayto/noticias/noticia/53044/orden-del-concurso-oficial-de-murgas-del" +
                    "-carnaval-de-badajoz-2023/",
            description = "LUNES 6  \n1. LOS YO NO SALGO - “LOS DE LA BARRA LIBRE” \n2. CHARRAMANGUEROS: LOS VALIENTES \n3. " +
                    "MURGA LOS GUADALUPINES “OYE MI CANTO” \n4. CS AL MARIDI “LOS QUE TE DAN GATO POR LIEBRE” \n5. CS LAS CHIMIXURRIS \n  \nMARTES 7  \n6. MURGUER QUEEN \n7. LOS OKUPAS - MURGA LOS CHUNGOS \n8. ASOC. CARNAVALERA MURGA DE TURUTA MADRE “LA ÚLTIMA..."
        ),
        News(
            title = "MEDIDAS DE TRÁFICO CON MOTIVO DE LA CABALGATA DE LOS REYES MAGOS",
            category = "Policía Local / Tráfico",
            date = "03/01/2023",
            link = "https://www.aytobadajoz" +
                    ".es/es/ayto/noticias/noticia/53032/medidas-de-trfico-con-motivo-de-la-cabalgata-de-los-reyes-magos/",
            description = "Con motivo de la celebración de la  Cabalgata de los Reyes Magos , en la tarde " +
                    "del día  5 de enero de 2023 , de acuerdo con el Bando de Alcaldía que regula las actividades del periodo navideño, se establecen las siguientes medidas con respecto al tráfico: \n  \nSe prohíbe el estacionamiento de vehículos:   \nEn la Calle Carmelo Vera Domenech, desde las..."
        ),
        News(
            title = "Servicio Administrativo de Planeamiento y Gestión.- Aprobación definitiva Estudio de Detalle submanzana V-7A de la UA-1, del SUB-CC-5.2.1",
            category = "Noticias Municipales / Urbanismo",
            date = "02/01/2023",
            link = "https://www.aytobadajozes/es/ayto/noticias/noticia/53031/servicio-administrativo-de-planeamiento-y-gestion-aprobacion-definitiva-estudio-de-detalle-submanzana-v-7a-de-la-ua-1-del-sub-cc-521/",
            description = "A  N  U  N  C  I  O   \\n            El Pleno del Excmo. Ayuntamiento de Badajoz en sesión celebrada con fecha \u00AD\u00AD\u00AD31 de octubre de 2022, adoptó acuerdo de aprobación definitiva del Estudio de Detalle (Refundido) de la submanzana V-7A de la UA-1 del SUB-CC-5.2.1, del Plan General Municipal de Badajoz, presentado por Urbanizadora..."

        )
    )
    NewsContent(
        state = HomeState.Success(NewsPage(news = news, next = 20, prev = 0)),
        onEvent = {}, onNavigationEvent = {}
    )
}