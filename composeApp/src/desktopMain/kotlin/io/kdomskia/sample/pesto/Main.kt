package io.kdomskia.sample.pesto

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Toolkit

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = initialWindowSize
        ),
        title = "Kdomskia",
        alwaysOnTop = true
    ) {
        App()
    }
}

private val fallbackScreenSize = DpSize(width = 800.dp, height = 600.dp)

private val maxInitialWindowSize = DpSize(width = 1800.dp, height = 900.dp)

private val screenSize: DpSize
    @Composable
    get() {
        return try {
            Toolkit.getDefaultToolkit().screenSize
                ?.let { DpSize(it.width.dp, it.height.dp) }
                ?: fallbackScreenSize
        } catch (e: Throwable) {
            e.printStackTrace()
            fallbackScreenSize
        }
    }

private val initialWindowSize: DpSize
    @Composable
    get() {
        val screenSize = screenSize
        val maxWindowSize = maxInitialWindowSize
        return DpSize(
            width = min(maxWindowSize.width, screenSize.width),
            height = min(maxWindowSize.height, screenSize.height)
        )
    }