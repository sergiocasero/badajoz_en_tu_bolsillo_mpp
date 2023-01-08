package com.badajoz.badajozentubolsillo.android.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.utils.MaterialColor
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

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
                    val manager = api.createPointAnnotationManager()
                    val allCoordinates = markers.map {
                        val point = Point.fromLngLat(it.longitude, it.latitude)
                        val options = PointAnnotationOptions()
                            .withPoint(Point.fromLngLat(it.longitude, it.latitude))
                            .withIconImage(bitmapFromDrawableRes(context, R.drawable.red_marker)!!)
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
fun BikeMarker(available: Int, notAvailable: Int) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        color = Color(MaterialColor.WHITE.tone(100, 0))
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Surface(
                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
                color = Color(MaterialColor.GREEN.tone(100, 0))
            ) {
                Text(
                    available.toString(),
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.primary)
                        .padding(start = 4.dp, end = 4.dp)
                )
            }
            Surface(shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)) {
                Text(
                    notAvailable.toString(),
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(start = 4.dp, end = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun BikeMarkerPreview() {
    BikeMarker(available = 10, notAvailable = 5)
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
            drawable.intrinsicWidth, drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap
    }
}