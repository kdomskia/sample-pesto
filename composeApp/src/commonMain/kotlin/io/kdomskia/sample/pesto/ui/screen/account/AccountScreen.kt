package io.kdomskia.sample.pesto.ui.screen.account

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.sample.pesto.ui.screen.wip.WorkInProgressScreen

@Composable
fun AccountScreen(
    contentPadding: PaddingValues,
    onClose: () -> Unit
) {
    WorkInProgressScreen(
        contentPadding = contentPadding,
        onClose = onClose
    )
}