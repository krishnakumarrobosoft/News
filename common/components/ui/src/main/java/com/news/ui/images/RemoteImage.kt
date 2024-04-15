package com.news.ui.images

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest

/**
 * Composable function that manages loading and display of remote images based on an [imageUrl].
 * The [imageUrl] is enriched of query parameters defining the required image size and quality for optimization.
 */
@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier
        .fillMaxSize(),
    imageUrl: String,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    optimizeImage: (String, Int) -> String,
    alpha: Float = DefaultAlpha,
    alignment: Alignment = Alignment.Center,
    preloadImageUri: String = "",
) {
    BoxWithConstraints(modifier = modifier) {
        val imageWidth = with(LocalDensity.current) { maxWidth.roundToPx() }
        val optimizedUrl = LocalContext.getOptimizedImageUrl(
            imageUrl,
            imageWidth,
            optimizeImage
        )

        if (preloadImageUri.isNotBlank()) {
            val optimizedPreloadUrl = LocalContext.getOptimizedImageUrl(
                preloadImageUri,
                imageWidth,
                optimizeImage
            )
            val requestPreload = ImageRequest.Builder(LocalContext.current.applicationContext)
                .data(optimizedPreloadUrl)
                .build()

            LocalContext.current.applicationContext.imageLoader.enqueue(requestPreload)
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current.applicationContext)
                .data(optimizedUrl)
                .build(),
            placeholder = if (placeholderRes == null) ColorPainter(Color.Gray) else painterResource(id = placeholderRes),
            error = if (errorRes == null) ColorPainter(Color.Gray) else painterResource(id = errorRes),
            contentDescription = contentDescription,
            contentScale = contentScale,
            alpha = alpha,
            modifier = imageModifier,
            alignment = alignment,
        )
    }
}

@Composable
private fun ProvidableCompositionLocal<Context>.getOptimizedImageUrl(
    imageUrl: String,
    imageWidth: Int,
    optimizeImage: (String, Int) -> String
): String {
    return runCatching {
        optimizeImage(imageUrl, imageWidth)
    }.getOrElse {
        imageUrl
    }
}

@Preview
@Composable
private fun RemoteImagePreview() {
    RemoteImage(
        modifier = Modifier
            .height(52.dp)
            .width(48.dp),
        imageUrl = "https://picsum.photos/200/300",
        contentDescription = "contentDescription",
        placeholderRes = androidx.vectordrawable.animated.R.drawable.notification_bg_low,
        errorRes = coil.base.R.drawable.notification_bg_low,
        optimizeImage = { _, _ -> "" }
    )
}
