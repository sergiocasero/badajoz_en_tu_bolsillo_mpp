package com.badajoz.badajozentubolsillo.android.composables.calendar.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem
import com.badajoz.badajozentubolsillo.viewmodel.CalendarState

@Preview
@Composable
fun CalendarContentPreview() {
    CalendarContent(
        state = CalendarState.Success(
            events = listOf(
                CalendarItem(
                    date = "8-01-2023",
                    location = "",
                    time = "",
                    title = "FESTIVO DE APERTURA DEL COMERCIO",
                    url = "https://www.aytobadajoz.es/es/ayto/agenda/evento/53012/festivo-de-apertura-del-comercio/"
                ),
                CalendarItem(
                    date = "29-01-2023",
                    location = "Plaza de la Libertad (Museo del Carnaval)",
                    time = "11:00",
                    title = "38º CROSS POPULAR VUELTA AL BALUARTE 2023",
                    url = "https://www.aytobadajoz.es/es/ayto/agenda/evento/52839/38-cross-popular-vuelta-al-baluarte-2023/"
                )
            )
        ),
        onEvent = {},
    )
}