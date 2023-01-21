package com.badajoz.badajozentubolsillo.android.composables.about.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard

@Composable
fun AboutCard(
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes buttonText: Int? = null,
    onClick: (@Composable () -> Unit)? = null
) {
    DefaultCard {
        Column {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun AboutCardPreview() {
    AboutCard(
        title = R.string.about_card_title_1,
        description = R.string.about_card_description_1,
        buttonText = R.string.about_card_link_1,
        onClick = {}
    )
}
