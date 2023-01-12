package com.badajoz.badajozentubolsillo.android.composables.menu.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.bike.BikeRoute
import com.badajoz.badajozentubolsillo.android.composables.bus.BusHomeRoute
import com.badajoz.badajozentubolsillo.android.composables.calendar.CalendarRoute
import com.badajoz.badajozentubolsillo.android.composables.fmd.FmdRoute
import com.badajoz.badajozentubolsillo.android.composables.menu.models.icon
import com.badajoz.badajozentubolsillo.android.composables.menu.models.screen
import com.badajoz.badajozentubolsillo.android.composables.menu.models.title
import com.badajoz.badajozentubolsillo.android.composables.news.NewsRoute
import com.badajoz.badajozentubolsillo.android.composables.pharmacy.PharmacyRoute
import com.badajoz.badajozentubolsillo.android.composables.reusable.EmptyView
import com.badajoz.badajozentubolsillo.android.composables.reusable.TopBar
import com.badajoz.badajozentubolsillo.android.composables.taxes.TaxesRoute
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.MenuEvent
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import kotlinx.coroutines.launch

@Composable
fun MenuContent(
    state: MenuState,
    onEvent: (MenuEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(title = state.title(), icon = Icons.Default.Menu) {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen || state !is MenuState.Bike,
        drawerContent = {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.size(24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painterResource(id = R.drawable.badajoz_logo),
                        contentDescription = stringResource(id = R.string.badajoz_logo),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(50.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(MaterialTheme.colors.background)
                ) {
                    Column {
                        MenuState.values().forEach {
                            DrawerItem(title = it.title(), icon = it.icon(), isCurrent = it == state) {
                                coroutineScope.launch { scaffoldState.drawerState.close() }
                                onNavigate(it.screen().toDestination())
                            }
                        }
                    }
                }
            }
        }
    ) {
        when (state) {
            MenuState.News -> NewsRoute { onNavigate(it) }
            MenuState.Bike -> BikeRoute { onNavigate(it) }
            MenuState.Bus -> BusHomeRoute { onNavigate(it) }
            MenuState.Calendar -> CalendarRoute()
            MenuState.Fmd -> FmdRoute { onNavigate(it) }
            MenuState.Minits -> EmptyView(
                message = stringResource(id = R.string.not_implemented_yet), icon = Icons
                    .Default.ThumbDown
            )

            MenuState.Pharmacy -> PharmacyRoute { onNavigate(it) }
            MenuState.Taxes -> TaxesRoute { onNavigate(it) }
        }
    }
}