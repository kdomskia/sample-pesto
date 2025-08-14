package io.kdomskia.sample.pesto

import io.kdomskia.compose.extension.onDomContentLoaded
import kotlinx.browser.document
import org.jetbrains.compose.web.renderComposableInBody

fun main() {
    document.onDomContentLoaded {
        renderComposableInBody {
            App()
        }
    }
}