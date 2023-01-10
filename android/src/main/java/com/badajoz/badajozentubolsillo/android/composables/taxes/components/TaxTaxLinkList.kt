package com.badajoz.badajozentubolsillo.android.composables.taxes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink

@Composable
fun TaxTaxLinkList(links: List<TaxLink>, onLinkClick: (TaxLink) -> Unit) {
    Column {
        links.forEach { link ->
            Text(
                text = link.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(6.dp)
                    .clickable { onLinkClick(link) },
            )
        }
    }
}