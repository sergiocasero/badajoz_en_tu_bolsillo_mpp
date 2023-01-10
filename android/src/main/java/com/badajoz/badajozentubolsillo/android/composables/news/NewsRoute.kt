package com.badajoz.badajozentubolsillo.android.composables.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.utils.MaterialColor
import com.badajoz.badajozentubolsillo.viewmodel.HomeState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsViewModel
import java.net.URLEncoder

@Composable
fun NewsRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { NewsViewModel(HomeState.InProgress) }

    NewsContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun NewsContent(
    state: HomeState,
    onNavigationEvent: (NavigationEvent) -> Unit,
    onEvent: (NewsEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(NewsEvent.Attach)
    }

    when (state) {
        is HomeState.InProgress -> LoadingView()
        is HomeState.Success -> Box {
            if (state.loadingMore) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f)
                ) {
                    LoadingView(background = Color(MaterialColor.WHITE.tone(100, 0)))
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f)
            ) {
                NewsSuccess(state.news, onEvent, onNavigationEvent)
            }
        }

        is HomeState.Error -> TODO()
    }
}

@Composable
fun NewsSuccess(news: List<News>, onEvent: (NewsEvent) -> Unit, onNavigationEvent: (NavigationEvent) -> Unit) {
    NewsList(news,
        onItemClick = {
            onNavigationEvent(NavigationEvent.OnNewsDetail(URLEncoder.encode(it.link, "UTF-8")))
        },
        onLoadMore = {
            onEvent(NewsEvent.OnLoadMore)
        })
}


@Composable
fun NewsList(news: List<News>, onItemClick: (News) -> Unit, onLoadMore: () -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(count = news.size + 1) { index ->
            if (index == news.size) {
                Button(onClick = { onLoadMore() }) {
                    Text("Cargar más")
                }
            } else {
                NewsItem(news = news[index]) { onItemClick(news[index]) }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsItem(news: News, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        elevation = defaultCardElevation
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colors.secondary
                ) {
                    Text(
                        text = news.category,
                        style = MaterialTheme.typography.subtitle2.apply {
                            copy(color = MaterialTheme.colors.onPrimary)
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Text(
                    text = news.date,
                    style = MaterialTheme.typography.subtitle2.apply {
                        copy(color = MaterialTheme.colors.onBackground)
                    }
                )
            }
            Row {
                Column {
                    Text(text = news.title, style = MaterialTheme.typography.h6)
                    Text(
                        text = news.description,
                        style = MaterialTheme.typography.body2,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Button(
                onClick = { onClick() },
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Ver")
            }
        }
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
        state = HomeState.Success(news, loadingMore = false),
        onEvent = {},
        onNavigationEvent = {}
    )
}