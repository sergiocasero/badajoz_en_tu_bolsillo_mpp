package com.badajoz.badajozentubolsillo.android.composables.pharmacy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard
import com.badajoz.badajozentubolsillo.android.composables.reusable.TextBox
import com.badajoz.badajozentubolsillo.model.category.pharmacy.Pharmacy
import com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyGroup
import com.badajoz.badajozentubolsillo.utils.MaterialColor

@Composable
fun PharmacySuccessView(pharmacy: List<PharmacyGroup>, onPharmacyClick: (Pharmacy) -> Unit) {
    val flatmap: List<Any> = pharmacy.flatMap { listOf(it) + it.items }
    LazyColumn {
        items(flatmap) { item ->
            when (item) {
                is PharmacyGroup -> Column {
                    Column {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                        Text(
                            text = item.openingTime,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                    }
                }

                is Pharmacy -> DefaultCard(
                    modifier = Modifier.height(60.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onPharmacyClick(item) }
                    ) {
                        TextBox(icon = Icons.Default.LocalPharmacy, color = Color(MaterialColor.GREEN.tone(700)))
                        Text(
                            text = item.address,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
