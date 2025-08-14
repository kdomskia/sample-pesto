package io.kdomskia.sample.pesto.ui.component

import io.kdomskia.compose.foundation.Image
import io.kdomskia.compose.material3.FloatingActionButton
import io.kdomskia.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.compose.ui.resource.img
import io.kdomskia.sample.pesto.Res
import io.kdomskia.sample.pesto.ic_edit
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EditFloatingActionButton(
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = { },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Image(
            img = Res.drawable.ic_edit.img,
            contentDescription = null
        )
    }
}