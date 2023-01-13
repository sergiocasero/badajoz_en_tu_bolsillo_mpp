package com.badajoz.badajozentubolsillo.android.composables.about

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.about.components.AboutCard
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import java.net.URLEncoder

@Composable
fun AboutRoute(onNavigate: (Destination) -> Unit) {
    LazyColumn {
        item {
            AboutCard(
                title = R.string.about_card_title_1,
                description = R.string.about_card_description_1,
                buttonText = R.string.about_card_link_1,
                onClick = {
                    onNavigate(
                        Screen.ExternalLink.toDestination(
                            URLEncoder.encode(stringResource(R.string.github), "UTF-8")
                        )
                    )
                }
            )
        }
        item {
            AboutCard(
                title = R.string.about_card_title_2,
                description = R.string.about_card_description_2,
                buttonText = R.string.about_card_link_2,
                onClick = {
                    onNavigate(
                        Screen.ExternalLink.toDestination(
                            URLEncoder.encode(stringResource(id = R.string.privacy), "UTF-8")
                        )
                    )
                }
            )
        }
        item {
            AboutCard(
                title = R.string.about_card_title_3,
                description = R.string.about_card_description_3
            )
        }
    }
}

@Preview
@Composable
fun AboutRoutePreview() {
    AboutRoute {}
}