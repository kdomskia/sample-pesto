package io.kdomskia.sample.pesto.ui.component.recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.layout.Box
import io.kdomskia.compose.foundation.layout.fillMaxSize
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.draw.clip
import io.kdomskia.compose.ui.layout.ContentScale
import io.kdomskia.compose.ui.resource.ImageResource

private const val IMAGE_PLACEHOLDER_ALPHA = 0.5f

@Composable
fun RecipeImage(
    modifier: Modifier,
    imgUrl: String,
    shape: Shape
) {
    val background = MaterialTheme.colorScheme
        .surfaceVariant.copy(alpha = IMAGE_PLACEHOLDER_ALPHA)
    Box(
        modifier = modifier
            .clip(shape)
            .background(background)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            img = ImageResource.Remote(imgUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}