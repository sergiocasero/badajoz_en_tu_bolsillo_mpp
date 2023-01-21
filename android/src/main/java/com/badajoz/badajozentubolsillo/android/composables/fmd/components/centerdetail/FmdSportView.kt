package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard
import com.badajoz.badajozentubolsillo.android.utils.staticUrl
import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportItem


@Composable
fun FmdSportView(
    appConfigData: AppConfigData,
    sport: FmdSportItem,
    onClick: () -> Unit
) {
    DefaultCard(
        modifier = Modifier.clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = sport.image.staticUrl(
                    user = appConfigData.user,
                    pass = appConfigData.pass,
                    context = LocalContext.current
                ),
                contentDescription = sport.title,
                modifier = Modifier
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sport.title,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Preview
@Composable
fun FmdSportViewPreview() {
    FmdSportView(
        sport = FmdSportItem(
            id = 0,
            title = "Pádel",
            image = "https://badajoz.sergiocasero.es/static/fmd/padel.png",
            centerId = 0
        ),
        onClick = {},
        appConfigData = AppConfigData("", "", "", "", 0, true)
    )
}