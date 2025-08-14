package io.kdomskia.sample.pesto.ui.size

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

val LocalFixedLeftFloatingContainerWidthState = compositionLocalOf {
    FixedLeftFloatingContainerWidthState()
}

@Stable
class FixedLeftFloatingContainerWidthState {

    var value: Int by mutableIntStateOf(0)

}