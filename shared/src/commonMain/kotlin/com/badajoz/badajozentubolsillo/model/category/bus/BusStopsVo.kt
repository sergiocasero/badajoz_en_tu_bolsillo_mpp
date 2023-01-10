package com.badajoz.badajozentubolsillo.model.category.bus

import kotlinx.serialization.Serializable

@Serializable
data class BusStopsVo(val stops: List<BusStop>)