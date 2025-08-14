package io.kdomskia.sample.pesto.ui.screen.wip

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.background
import io.kdomskia.compose.foundation.layout.Column
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.Spacer
import io.kdomskia.compose.foundation.layout.fillMaxWidth
import io.kdomskia.compose.foundation.layout.fillViewportHeight
import io.kdomskia.compose.foundation.layout.padding
import io.kdomskia.compose.material3.MaterialTheme
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.sample.pesto.ui.component.AppBar
import io.kdomskia.sample.pesto.ui.component.AppBarIconType
import io.kdomskia.sample.pesto.ui.component.UnavailableContent
import io.kdomskia.sample.pesto.ui.component.UnavailableContentType

@Composable
fun WorkInProgressScreen(
    contentPadding: PaddingValues,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillViewportHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(contentPadding)
    ) {
        AppBar(
            onIconClick = onClose,
            iconType = AppBarIconType.Close
        )
        Spacer(modifier = Modifier.weight(0.5f))
        UnavailableContent(
            type = UnavailableContentType.WorkInProgress
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}