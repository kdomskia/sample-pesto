package io.kdomskia.sample.pesto.ui.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import io.kdomskia.sample.pesto.ui.extension.toPx
import kotlin.math.roundToInt

class DottedShape(
    private val width: Dp,
    private val spacing: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        Path().apply {
            val widthPx = width.toPx(density)
            val spacingPx = spacing.toPx(density)
            val stepPx = widthPx + spacingPx
            val stepsCount = (size.width / stepPx).roundToInt()
            val dotSize = Size(width = widthPx, height = size.height)
            for (i in 0 until stepsCount) {
                addRect(
                    Rect(
                        offset = Offset(x = i * stepPx, y = 0f),
                        size = dotSize
                    )
                )
            }
            close()
        }
    )

}