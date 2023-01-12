package com.badajoz.badajozentubolsillo.android.composables.bus.components.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.bus.components.detail.StopItemView
import com.badajoz.badajozentubolsillo.android.composables.reusable.EmptyView
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop

@Composable
fun FavoriteStopsView(stops: List<BusStop>, onItemClick: (BusStop) -> Unit, onFavoriteClick: (BusStop) -> Unit) {
    if (stops.isEmpty()) {
        EmptyView(
            message = stringResource(id = R.string.no_favorite_stops_yet),
            icon = Icons.Filled.FavoriteBorder
        )
    } else {
        LazyColumn {
            items(stops) { busStop ->
                StopItemView(stop = busStop,
                    onClick = { onItemClick(busStop) },
                    onFavoriteClick = { onFavoriteClick(busStop) }
                )
            }
        }
    }
}