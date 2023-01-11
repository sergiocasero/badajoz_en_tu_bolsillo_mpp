package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportItem
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdSportsListView(
    sports: List<FmdSportItem>,
    onNavigate: (Destination) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 4.dp,
            top = 4.dp,
            end = 4.dp,
            bottom = 4.dp
        ),
        content = {
            items(sports) { sport ->
                FmdSportView(sport) { onNavigate(Screen.FmdSportDetail.toDestination(sport.centerId, sport.id)) }
            }
        }
    )
}

@Preview
@Composable
fun FmdSportsListViewPreview() {
    FmdSportsListView(
        sports = listOf(
            FmdSportItem(title = "Pádel", image = "", centerId = 0, id = 0),
            FmdSportItem(title = "Fútbol sala", image = "", centerId = 0, id = 0),
            FmdSportItem(title = "Gimnasio", image = "", centerId = 0, id = 0),
            FmdSportItem(title = "Piscina climatizada", image = "", centerId = 0, id = 0),
            FmdSportItem(title = "Squash", image = "", centerId = 0, id = 0),
            FmdSportItem(title = "Fútbol 7", image = "", centerId = 0, id = 0),
            FmdSportItem(title = "Tenis", image = "", centerId = 0, id = 0),
        ),
        onNavigate = {}
    )
}