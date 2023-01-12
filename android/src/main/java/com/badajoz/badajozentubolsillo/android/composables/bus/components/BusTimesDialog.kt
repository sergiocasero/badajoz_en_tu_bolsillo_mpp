package com.badajoz.badajozentubolsillo.android.composables.bus.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.model.category.bus.BusStopDetail
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailEvent

@Composable
fun BusTimesDialog(selectedStop: BusStopDetail, onDismiss: () -> Unit) {
    AlertDialog(
        title = { Text(text = selectedStop.stop.name) },
        text = {
            Column {
                selectedStop.times.forEach {
                    Row(
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = it.line,
                            modifier = Modifier.width(30.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.next_bus_at, it.time))
                    }
                }
            }
        },
        onDismissRequest = { onDismiss() },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    onClick = { onDismiss() }
                ) {
                    Text(text = stringResource(id = R.string.close))
                }
            }
        }
    )
}