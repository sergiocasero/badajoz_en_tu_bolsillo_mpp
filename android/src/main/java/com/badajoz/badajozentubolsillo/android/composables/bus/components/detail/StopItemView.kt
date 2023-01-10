package com.badajoz.badajozentubolsillo.android.composables.bus.components.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop

@Composable
fun StopItemView(stop: BusStop, onClick: (BusStop) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = defaultCardElevation
    ) {
        Row {
            Text(
                stop.name, modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.body1
            )
            IconButton(onClick = {
                onClick(stop.copy(favorite = !stop.favorite))
            }) {
                Icon(
                    if (stop.favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Marcar como favorita"
                )
            }
        }

    }
}