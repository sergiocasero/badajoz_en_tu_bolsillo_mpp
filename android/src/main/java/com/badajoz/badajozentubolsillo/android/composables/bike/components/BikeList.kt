package com.badajoz.badajozentubolsillo.android.composables.bike.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.android.utils.staticUrl
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation

@Composable
fun BikeList(bikeStations: List<BikeStation>, onBikeClick: (BikeStation) -> Unit) {
    LazyColumn {
        items(bikeStations) { bikeStation ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onBikeClick(bikeStation) },
                elevation = defaultCardElevation
            ) {
                Row(
                    modifier = Modifier.height(100.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = bikeStation.images.first().staticUrl(LocalContext.current),
                        modifier = Modifier.width(100.dp),
                        contentDescription = bikeStation.name,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = bikeStation.name,
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Disponibles: ${bikeStation.availableBikes}",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "No disponibles: ${bikeStation.notAvailableBikes}",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
    }
}