package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCenterItemView(
    center: FmdCenterItem,
    onNavigate: (Screen) -> Unit
) {

}

@Preview
@Composable
fun FmdCenterItemPreview() {
    FmdCenterItemView(
        center = FmdCenterItem(
            id = 0,
            title = "Piscina Municipal",
            image = "",
            location = "Calle de la Piscina, 1",
            phone = "924 00 00 00",
        ),
        onNavigate = {}
    )
}