package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.ic_logo
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppLogo(
    modifier: Modifier = Modifier
) {
    Image(
        img = Res.drawable.ic_logo.img,
        contentDescription = null,
        modifier = modifier
    )
}