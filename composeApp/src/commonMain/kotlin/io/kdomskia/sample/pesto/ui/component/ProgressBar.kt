package io.kdomskia.sample.pesto.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.height
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.sample.pesto.ui.res.dimens

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier
) {
    Spacer(
        Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(dimens.progressBarHeight)
    )
}