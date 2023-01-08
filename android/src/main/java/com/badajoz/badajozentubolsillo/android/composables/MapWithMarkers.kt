package com.badajoz.badajozentubolsillo.android.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createCircleAnnotationManager

data class Marker(val latitude: Double, val longitude: Double, val title: String)

@Composable
fun MapWithMarkers(markers: List<Marker>) {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                val map = getMapboxMap()
                map.loadStyleUri(Style.MAPBOX_STREETS)
                map.addOnStyleLoadedListener {
                    val api = annotations
                    val manager = api.createCircleAnnotationManager()
                    val allCoordinates = markers.map {
                        val point = Point.fromLngLat(it.longitude, it.latitude)
                        val options = CircleAnnotationOptions()
                            .withPoint(Point.fromLngLat(18.06, 59.31))
                            // Style the circle that will be added to the map.
                            .withCircleRadius(8.0)
                            .withCircleColor("#ee4e8b")
                            .withCircleStrokeWidth(2.0)
                            .withCircleStrokeColor("#ffffff")
                        // .withIconImage(bitmapFromDrawableRes(context, R.drawable.)!!)
                        manager.create(options)
                        point
                    }

                    val polygon = Polygon.fromLngLats(listOf(allCoordinates))

                    val position = map.cameraForGeometry(polygon, EdgeInsets(100.0, 100.0, 100.0, 100.0))
                    map.flyTo(position)
                }
            }
        }
    )
}

private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
    convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
    if (sourceDrawable == null) {
        return null
    }
    return if (sourceDrawable is BitmapDrawable) {
        sourceDrawable.bitmap
    } else {
// copying drawable object to not manipulate on the same reference
        val constantState = sourceDrawable.constantState ?: return null
        val drawable = constantState.newDrawable().mutate()
        val bitmap: Bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth, drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap
    }
}