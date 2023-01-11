package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCentersList(
    centers: List<FmdCenterItem>,
    onNavigate: (Destination) -> Unit
) {
    LazyColumn {
        items(centers) { center ->
            FmdCenterItemView(
                center = center,
                onNavigate = onNavigate
            )
        }
    }
}