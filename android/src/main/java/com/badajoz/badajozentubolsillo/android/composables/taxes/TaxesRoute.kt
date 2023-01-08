package com.badajoz.badajozentubolsillo.android.composables.taxes


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModel
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelEvent
import com.badajoz.badajozentubolsillo.viewmodel.TaxesViewModelState

@Composable
fun TaxesRoute() {
    val viewModel = remember { TaxesViewModel(initialState = TaxesViewModelState.InProgress) }

    TaxesContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun TaxesContent(state: TaxesViewModelState, onEvent: (TaxesViewModelEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(TaxesViewModelEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is TaxesViewModelState.InProgress -> LoadingView()
                is TaxesViewModelState.Error -> TODO()
                is TaxesViewModelState.Success -> TaxesSuccess(state.taxes)
            }
        }
    )
}

@Composable
fun TaxesSuccess(taxes: List<TaxGroup>) {
    LazyColumn {
        items(taxes) { tax ->
            TaxGroupCard(tax)
        }
    }
}

@Composable
fun TaxGroupCard(taxGroup: TaxGroup) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = taxGroup.header,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .padding(8.dp)
            )

            TaxTaxLinkList(taxGroup.links)

            taxGroup.child.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Column {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.secondary)
                                .padding(8.dp)
                        )
                        TaxTaxLinkList(it.links)
                    }
                }
            }
        }
    }
}

@Composable
fun TaxTaxLinkList(links: List<TaxLink>) {
    Column {
        links.forEach { link ->
            Text(
                text = link.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}