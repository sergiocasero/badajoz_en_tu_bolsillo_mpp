package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSport

@Composable
fun FmdSportsListView(sports: List<FmdSport>) {
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
                FmdSportView(sport)
            }
        }
    )
}

@Preview
@Composable
fun FmdSportsListViewPreview() {
    FmdSportsListView(
        sports = listOf(
            FmdSport(criteria = "PÁDEL", id = 0, name = "Pádel", image = ""),
            FmdSport(criteria = "SALA", id = 1, name = "Fútbol sala", image = ""),
            FmdSport(criteria = "GIMNASIO", id = 2, name = "Gimnasio", image = ""),
            FmdSport(criteria = "Pisc. CLIM.", id = 3, name = "Piscina climatizada", image = ""),
            FmdSport(criteria = "SQUASH", id = 4, name = "Squash", image = ""),
            FmdSport(criteria = "FÚTBOL 7", id = 5, name = "Fútbol 7", image = ""),
            FmdSport(criteria = "TENIS", id = 6, name = "Tenis", image = "")
        )
    )
}