package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.model.category.news.NewsCategory
import com.badajoz.badajozentubolsillo.utils.MaterialColor
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.news.HomeState
import com.badajoz.badajozentubolsillo.viewmodel.news.NewsEvent

@Composable
fun NewsContent(
    state: HomeState,
    onEvent: (NewsEvent) -> Unit,
    onNavigate: (Destination) -> Unit
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
                NewsSuccess(state.news, onEvent, onNavigate)
            }
        }

        is HomeState.Error -> ErrorView(error = state.error) { onEvent(NewsEvent.Attach) }
    }
}

@Preview
@Composable
fun NewsContentPreview() {
    val news = listOf(
        News(
            title = "Orden del Concurso Oficial de Murgas del Carnaval de Badajoz 2023",
            category =
            NewsCategory(
                id = "Ferias y Fiestas / Carnaval",
                name = "Ferias y Fiestas / Carnaval",
                color = "#F44336"
            ),
            date = "07/01/2023",
            link = "https://www.aytobadajoz.es/es/ayto/noticias/noticia/53044/orden-del-concurso-oficial-de-murgas-del" +
                    "-carnaval-de-badajoz-2023/",
            description = "LUNES 6  \n1. LOS YO NO SALGO - ???LOS DE LA BARRA LIBRE??? \n2. CHARRAMANGUEROS: LOS VALIENTES \n3. " +
                    "MURGA LOS GUADALUPINES ???OYE MI CANTO??? \n4. CS AL MARIDI ???LOS QUE TE DAN GATO POR LIEBRE??? \n5. CS LAS CHIMIXURRIS \n?? \nMARTES 7  \n6. MURGUER QUEEN \n7. LOS OKUPAS - MURGA LOS CHUNGOS \n8. ASOC. CARNAVALERA MURGA DE TURUTA MADRE ???LA ??LTIMA..."
        ),
        News(
            title = "MEDIDAS DE TR??FICO CON MOTIVO DE LA CABALGATA DE LOS REYES MAGOS",
            category = NewsCategory(
                id = "Ferias y Fiestas / Carnaval",
                name = "Ferias y Fiestas / Carnaval",
                color = "#F44336"
            ),
            date = "03/01/2023",
            link = "https://www.aytobadajoz" +
                    ".es/es/ayto/noticias/noticia/53032/medidas-de-trfico-con-motivo-de-la-cabalgata-de-los-reyes-magos/",
            description = "Con motivo de la celebraci??n de la  Cabalgata de los Reyes Magos , en la tarde " +
                    "del d??a  5 de enero de 2023 , de acuerdo con el Bando de Alcald??a que regula las actividades del periodo navide??o, se establecen las siguientes medidas con respecto al tr??fico: \n?? \nSe proh??be el estacionamiento de veh??culos:   \nEn la Calle Carmelo Vera Domenech, desde las..."
        ),
        News(
            title = "Servicio Administrativo de Planeamiento y Gesti??n.- Aprobaci??n definitiva Estudio de Detalle submanzana V-7A de la UA-1, del SUB-CC-5.2.1",
            category = NewsCategory(
                id = "Ferias y Fiestas / Carnaval",
                name = "Ferias y Fiestas / Carnaval",
                color = "#F44336"
            ),
            date = "02/01/2023",
            link = "https://www.aytobadajozes/es/ayto/noticias/noticia/53031/servicio-administrativo-de-planeamiento-y-gestion-aprobacion-definitiva-estudio-de-detalle-submanzana-v-7a-de-la-ua-1-del-sub-cc-521/",
            description = "A?? N?? U?? N?? C?? I?? O   \\n?? ?? ?? ?? ?? ?? El Pleno del Excmo. Ayuntamiento de Badajoz en sesi??n celebrada con fecha \u00AD\u00AD\u00AD31 de octubre de 2022, adopt?? acuerdo de aprobaci??n definitiva del Estudio de Detalle (Refundido) de la submanzana V-7A de la UA-1 del SUB-CC-5.2.1, del Plan General Municipal de Badajoz, presentado por Urbanizadora..."

        )
    )
    NewsContent(
        state = HomeState.Success(news, loadingMore = false),
        onEvent = {},
        onNavigate = {}
    )
}