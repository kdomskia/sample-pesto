package io.kdomskia.sample.pesto.ui.extension

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun <T> byWidthSizeClass(
    compact: @Composable () -> T,
    medium: @Composable () -> T,
    expanded: @Composable () -> T
): T = when (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass) {
    WindowWidthSizeClass.COMPACT -> compact()
    WindowWidthSizeClass.MEDIUM -> medium()
    WindowWidthSizeClass.EXPANDED -> expanded()
    else -> compact()
}

@Composable
fun <T> byHeightSizeClass(
    compact: @Composable () -> T,
    medium: @Composable () -> T,
    expanded: @Composable () -> T
): T = when (currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass) {
    WindowHeightSizeClass.COMPACT -> compact()
    WindowHeightSizeClass.MEDIUM -> medium()
    WindowHeightSizeClass.EXPANDED -> expanded()
    else -> compact()
}