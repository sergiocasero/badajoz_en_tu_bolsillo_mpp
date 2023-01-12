package com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerlist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.viewmodel.Destination

@Composable
fun FmdCentersList(
    appConfigData: AppConfigData,
    centers: List<FmdCenterItem>,
    onNavigate: (Destination) -> Unit
) {
    LazyColumn {
        items(centers) { center ->
            FmdCenterItemView(
                appConfigData,
                center = center,
                onNavigate = onNavigate
            )
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}