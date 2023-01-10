package com.badajoz.badajozentubolsillo.android.composables.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem
import com.badajoz.badajozentubolsillo.viewmodel.CalendarEvent
import com.badajoz.badajozentubolsillo.viewmodel.CalendarState
import com.badajoz.badajozentubolsillo.viewmodel.CalendarViewModel

@Composable
fun CalendarRoute() {
    val viewModel = remember { CalendarViewModel(initialState = CalendarState.InProgress) }

    CalendarContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun CalendarContent(state: CalendarState, onEvent: (CalendarEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(CalendarEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is CalendarState.InProgress -> LoadingView()
                is CalendarState.Error -> TODO()
                is CalendarState.Success -> CalendarSuccess(state.events)
            }
        }
    )
}

@Composable
fun CalendarSuccess(events: List<CalendarItem>) {
    LazyColumn {
        items(events.size) { index ->
            CalendarEventItem(events[index])
        }
    }
}

@Composable
fun CalendarEventItem(item: CalendarItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        elevation = defaultCardElevation
    ) {
        Column {
            Text(
                text = item.title, style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(4.dp)
            )
            if (item.date.isNotBlank()) {
                CalendarFieldRow(label = item.date, icon = Icons.Default.CalendarToday)
            }
            if (item.time.isNotBlank()) {
                CalendarFieldRow(label = item.time, icon = Icons.Default.Timer)
            }
            if (item.location.isNotBlank()) {
                CalendarFieldRow(label = item.location, icon = Icons.Default.LocationOn)
            }
        }
    }
}

@Composable
fun CalendarFieldRow(label: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon, contentDescription = label,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = label, style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

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