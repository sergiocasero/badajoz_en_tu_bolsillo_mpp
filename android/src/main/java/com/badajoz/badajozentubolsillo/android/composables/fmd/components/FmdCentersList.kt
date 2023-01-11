package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCentersList(
    centers: List<FmdCenterItem>,
    onNavigate: (Screen) -> Unit
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