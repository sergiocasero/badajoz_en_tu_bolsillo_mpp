package com.badajoz.badajozentubolsillo.android.composables.news.components.detail

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.reusable.TopBar
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import com.badajoz.badajozentubolsillo.model.category.news.NewsDownload
import com.badajoz.badajozentubolsillo.model.category.news.NewsImg
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailState
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun NewsDetailContent(
    state: NewsDetailState,
    onEvent: (NewsDetailEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(NewsDetailEvent.Attach)
    }

    Scaffold(topBar = {
        if (state is NewsDetailState.Success) {
            TopBar(title = state.newsDetail.title, icon = Icons.Default.ArrowBack, onNavClick = {
                onNavigate(Screen.News.toDestination())
            })
        }
    },
        content = {
            when (state) {
                is NewsDetailState.InProgress -> LoadingView()
                is NewsDetailState.Success -> SuccessView(state.newsDetail)
                is NewsDetailState.Error -> ErrorView(error = state.error) { onEvent(NewsDetailEvent.Attach) }
            }
        }
    )
}

@Preview
@Composable
fun NewsDetailContentPreview() {
    SuccessView(
        NewsDetail(
            title = "Orden del Concurso Oficial de Murgas del Carnaval de Badajoz 2023",
            content = """
                <div>
                <strong>LUNES 6</strong></div>
                <div>
                1. LOS YO NO SALGO - “LOS DE LA BARRA LIBRE”</div>
                <div>
                2. CHARRAMANGUEROS: LOS VALIENTES</div>
                <div>
                3. MURGA LOS GUADALUPINES “OYE MI CANTO”</div>
                <div>
                4. CS AL MARIDI “LOS QUE TE DAN GATO POR LIEBRE”</div>
                <div>
                5. CS LAS CHIMIXURRIS</div>
                <div>
                &nbsp;</div>
                <div>
                <strong>MARTES 7</strong></div>
                <div>
                6. MURGUER QUEEN</div>
                <div>
                7. LOS OKUPAS - MURGA LOS CHUNGOS</div>
                <div>
                8. ASOC. CARNAVALERA MURGA DE TURUTA MADRE “LA ÚLTIMA Y NOS VAMOS”</div>
                <div>
                9. LOS ROMPECORAZONES - LOS 3W</div>
                <div>
                10. CS MARWAN CHIILIQUI “EL HIJO DESASTRE”</div>
                <div>
                &nbsp;</div>
                <div>
                <strong>MIÉRCOLES 8</strong></div>
                <div>
                11. LOS MIRINDA PRESENTAN: VACCACIONES SANTILLANAS</div>
                <div>
                12. MURGA LOS LINGARTOS</div>
                <div>
                13. EL DON JUAN DE TUS DETALLES</div>
                <div>
                14. MURGA DE LA GARROVILLA “LOS NOVATOS”</div>
                <div>
                15. CS LOS CHICOS DEL CORRO (LOS CAMBALLOTAS)</div>
                <div>
                &nbsp;</div>
                <div>
                <strong>JUEVES 9</strong></div>
                <div>
                16. LOS ESPANTAPERROS</div>
                <div>
                17. LOS MINI-FOLK</div>
                <div>
                18. PA 4 DÍAS, O PA 10 DÍAS (SI LLEGAMOS) EN BLANCO Y NEGRO</div>
                <div>
                19. LOS ESCUSAOS</div>
                <div>
                20. CS COMUNAVERGA (LOS WATERCLOSET)</div>
                <div>
                &nbsp;</div>
                <div>
                <strong>VIERNES 10</strong></div>
                <div>
                21. MURGA SA TERSIAO “EL PARTIDO POPULAR”</div>
                <div>
                22. MURGA A CONTRAGOLPE “PEDRO EL TORPE”</div>
                <div>
                23. ASOCIACIÓN MURGA LA CASTAFIORE</div>
                <div>
                24. CS LOS NOVELEROS (MURGA LOS NIÑOS)</div>, datetime=07.01.2023
            """.trimIndent(),
            downloads = listOf(
                NewsDownload(
                    title = "sorteomurgas2",
                    link = "https://www.aytobadajoz.es/imagenes/ayto/2023_01/sorteomurgas2.jpg"
                ),
                NewsDownload(
                    title = "sorteomurgas3",
                    link = "https://www.aytobadajoz.es/imagenes/ayto/2023_01/sorteomurgas3.jpg"
                ),
                NewsDownload(
                    title = "sorteomurgas4",
                    link = "https://www.aytobadajoz.es/imagenes/ayto/2023_01/sorteomurgas4.jpg"
                ),
                NewsDownload(
                    title = "sorteomurgas5",
                    link = "https://www.aytobadajoz.es/imagenes/ayto/2023_01/sorteomurgas5.jpg"
                )
            ),
            img = NewsImg(
                alt = "Orden del Concurso Oficial de Murgas del Carnaval de Badajoz 2023",
                url = "https://www.aytobadajoz.es/imagenes/ayto/2023_01/sorteomurgas1.jpg"
            ),
            datetime = "07.01.2023"
        )
    )
}