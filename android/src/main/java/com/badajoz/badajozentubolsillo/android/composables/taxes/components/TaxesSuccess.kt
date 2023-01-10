package com.badajoz.badajozentubolsillo.android.composables.taxes.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink

@Composable
fun TaxesSuccess(taxes: List<TaxGroup>, onLinkClick: (TaxLink) -> Unit) {
    LazyColumn {
        items(taxes) { tax ->
            TaxGroupCard(tax) { onLinkClick(it) }
        }
    }
}