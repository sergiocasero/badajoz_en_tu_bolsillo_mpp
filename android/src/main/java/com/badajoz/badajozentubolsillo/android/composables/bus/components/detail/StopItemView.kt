package com.badajoz.badajozentubolsillo.android.composables.bus.components.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop

@Composable
fun StopItemView(stop: BusStop, onClick: () -> Unit, onFavoriteClick: (BusStop) -> Unit) {
    DefaultCard {
        Row {
            Text(
                stop.name, modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.body1
            )
            IconButton(onClick = { onClick() }) {
                Icon(
                    Icons.Default.AccessTime,
                    contentDescription = stringResource(id = R.string.see_times)
                )
            }
            IconButton(onClick = {
                onFavoriteClick(stop.copy(favorite = !stop.favorite))
            }) {
                Icon(
                    if (stop.favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.mark_as_favorite)
                )
            }
        }

    }
}