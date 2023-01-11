package com.badajoz.badajozentubolsillo.android.composables.fmd.components.sportdetail

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportDetail

@Composable
fun FmdSportDetailSuccess(sport: FmdSportDetail) {
    val days = sport.days

    LazyColumn {
        items(days) { day ->
            FmdSportDetailDay(day)
        }
    }
}