package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
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

@Composable
fun FmdSportView(sport: FmdSport) {
    Card(
        elevation = defaultCardElevation,
        modifier = Modifier
            .padding(4.dp)
            .width(40.dp)
            .height(80.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = sport.name,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Preview
@Composable
fun FmdSportsListViewPreview() {
    FmdSportsListView(
        sports = listOf(
            FmdSport(criteria = "PÁDEL", id = 0, name = "Pádel"),
            FmdSport(criteria = "SALA", id = 1, name = "Fútbol sala"),
            FmdSport(criteria = "GIMNASIO", id = 2, name = "Gimnasio"),
            FmdSport(criteria = "Pisc. CLIM.", id = 3, name = "Piscina climatizada"),
            FmdSport(criteria = "SQUASH", id = 4, name = "Squash"),
            FmdSport(criteria = "FÚTBOL 7", id = 5, name = "Fútbol 7"),
            FmdSport(criteria = "TENIS", id = 6, name = "Tenis")
        )
    )
}