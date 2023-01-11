package com.badajoz.badajozentubolsillo.android.composables.bus.components.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.android.composables.bus.components.detail.StopItemView
import com.badajoz.badajozentubolsillo.android.composables.reusable.EmptyView
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop

@Composable
fun FavoriteStopsView(stops: List<BusStop>, onItemClick: (BusStop) -> Unit, onFavoriteClick: (BusStop) -> Unit) {
    if (stops.isEmpty()) {
        EmptyView(
            message = "Aún no tienes paradas favoritas, añádelas en el listado de paradas dentro de cada línea",
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