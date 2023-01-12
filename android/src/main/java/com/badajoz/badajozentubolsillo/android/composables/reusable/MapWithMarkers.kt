package com.badajoz.badajozentubolsillo.android.composables.reusable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import com.badajoz.badajozentubolsillo.android.R
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

@Composable
fun MapWithMarkers(markers: List<Marker>) {

    val markerBitMaps =

        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    val map = getMapboxMap()
                    map.loadStyleUri(Style.MAPBOX_STREETS)
                    map.addOnStyleLoadedListener {
                        val api = annotations
                        val allCoordinates = markers.map {
                            val manager = api.createPointAnnotationManager()
                            val point = Point.fromLngLat(it.longitude, it.latitude)
                            val options = PointAnnotationOptions()
                                .withPoint(Point.fromLngLat(it.longitude, it.latitude))
                                .withIconImage(bitmapFromDrawableRes(context, R.drawable.item_bike_map)!!)
                                .withTextField(it.title)
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

@Composable
fun CaptureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {

    val context = LocalContext.current

    /**
     * ComposeView that would take composable as its content
     * Kept in remember so recomposition doesn't re-initialize it
     **/
    val composeView = remember { ComposeView(context) }

    /**
     * Callback function which could get latest image bitmap
     **/
    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    /** Use Native View inside Composable **/
    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    /** returning callback to bitmap **/
    return ::captureBitmap
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
            140, 70,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap
    }
}