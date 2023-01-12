package com.badajoz.badajozentubolsillo.android.composables.bus.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.badajoz.badajozentubolsillo.android.composables.bus.components.home.BusLineItemView
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineItem

@Composable
fun BusLinesView(lines: List<BusLineItem>, onLineClick: (BusLineItem) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(lines) { busLine ->
            BusLineItemView(line = busLine) { onLineClick(busLine) }
        }
    }
}