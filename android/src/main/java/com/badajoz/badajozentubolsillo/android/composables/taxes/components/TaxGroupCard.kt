package com.badajoz.badajozentubolsillo.android.composables.taxes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink

@Composable
fun TaxGroupCard(taxGroup: TaxGroup, onLinkClick: (TaxLink) -> Unit) {
    DefaultCard {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = taxGroup.header,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TaxTaxLinkList(taxGroup.links) { onLinkClick(it) }

            taxGroup.child.forEach {
                DefaultCard(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Column {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        )
                        TaxTaxLinkList(it.links) { onLinkClick(it) }
                    }
                }
            }
        }
    }
}