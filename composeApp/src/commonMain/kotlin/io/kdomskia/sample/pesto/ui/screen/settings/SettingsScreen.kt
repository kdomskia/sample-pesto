package io.kdomskia.sample.pesto.ui.screen.settings

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.sample.pesto.ui.screen.wip.WorkInProgressScreen

@Composable
fun SettingsScreen(
    contentPadding: PaddingValues,
    onClose: () -> Unit
) {
    WorkInProgressScreen(
        contentPadding = contentPadding,
        onClose = onClose
    )
}