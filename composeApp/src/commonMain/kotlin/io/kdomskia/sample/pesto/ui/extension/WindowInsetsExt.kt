package io.kdomskia.sample.pesto.ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import io.kdomskia.compose.foundation.layout.PaddingValues
import io.kdomskia.compose.foundation.layout.WindowInsets
import io.kdomskia.compose.foundation.layout.WindowInsetsSides
import io.kdomskia.compose.foundation.layout.asPaddingValues
import io.kdomskia.compose.foundation.layout.exclude
import io.kdomskia.compose.foundation.layout.only

@Composable
fun WindowInsets.asPaddingValues(
    exclude: WindowInsetsSides
): PaddingValues = remember {
    val insets = this
    insets.exclude(insets.only(exclude))
}.asPaddingValues()

@Composable
fun WindowInsets.getBottom(): Dp = asPaddingValues().calculateBottomPadding()