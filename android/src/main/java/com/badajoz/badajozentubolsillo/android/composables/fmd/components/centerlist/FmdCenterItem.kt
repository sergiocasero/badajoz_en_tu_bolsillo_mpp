package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.android.composables.reusable.FieldRow
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.android.utils.staticUrl
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCenterItemView(
    center: FmdCenterItem,
    onNavigate: (Screen) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = defaultCardElevation
    ) {

        Column {
            AsyncImage(
                model = center.image.staticUrl(LocalContext.current),
                contentDescription = center.title,
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = center.title,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.weight(1f))
            if (center.location.isNotBlank()) {
                FieldRow(label = center.location, icon = Icons.Default.Place) {
                    onNavigate(Screen.MapLink(center.location))
                }
            }
            FieldRow(label = center.phone, icon = Icons.Default.Phone) {
                onNavigate(Screen.ExternalLink(center.phone))
            }
            Button(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.End),
                onClick = { onNavigate(Screen.FmdCenterDetail(center.id)) }) {
                Text(text = "Ver deportes")
            }
        }
    }
}

@Preview
@Composable
fun FmdCenterItemPreview() {
    FmdCenterItemView(
        center = FmdCenterItem(
            id = 0,
            title = "Piscina Municipal",
            image = "",
            location = "Calle de la Piscina, 1",
            phone = "924 00 00 00",
        ),
        onNavigate = {}
    )
}