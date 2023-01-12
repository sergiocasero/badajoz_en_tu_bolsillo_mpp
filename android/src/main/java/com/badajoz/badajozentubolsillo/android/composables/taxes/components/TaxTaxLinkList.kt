package com.badajoz.badajozentubolsillo.android.composables.taxes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink

@Composable
fun TaxTaxLinkList(links: List<TaxLink>, onLinkClick: (TaxLink) -> Unit) {
    Column {
        links.forEach { link ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = link.title,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(6.dp)
                        .weight(1f),
                )
                IconButton(
                    onClick = { onLinkClick(link) },
                    modifier = Modifier.width(40.dp)
                ) {
                    Icon(
                        Icons.Default.OpenInNew,
                        contentDescription = "Ver horarios"
                    )
                }
            }
        }
    }
}