package io.kdomskia.sample.pesto.ui.extension

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

fun Dp.toPx(density: Density): Float = density.run { roundToPx().toFloat() }