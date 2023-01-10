package com.badajoz.badajozentubolsillo.android.composables.bike.models

import com.badajoz.badajozentubolsillo.android.composables.reusable.Marker
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation

fun BikeStation.toMarker() = Marker(
    title = "$availableBikes/$notAvailableBikes",
    latitude = lat,
    longitude = lng
)