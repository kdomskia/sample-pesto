package io.kdomskia.sample.pesto.ui.extension

import androidx.compose.runtime.Composable
import io.kdomskia.compose.foundation.layout.fillViewportHeight
import io.kdomskia.compose.ui.Modifier
import io.kdomskia.sample.pesto.ui.component.MaxSizedCircularProgressIndicator
import io.kdomskia.sample.pesto.ui.component.UnavailableContent
import io.kdomskia.sample.pesto.ui.component.UnavailableContentType
import io.kdomskia.sample.pesto.ui.model.fetch.FetchState

@Composable
inline fun <reified T, reified E> FetchState<T, E>.use(
    idle: @Composable () -> Unit = { },
    loading: @Composable () -> Unit = { MaxSizedCircularProgressIndicator() },
    success: @Composable (T) -> Unit,
    error: @Composable (E) -> Unit = {
        UnavailableContent(
            modifier = Modifier.fillViewportHeight(),
            type = UnavailableContentType.Error
        )
    }
) {
    when (this) {
        is FetchState.Idle -> idle()
        is FetchState.Loading -> loading()
        is FetchState.Success<T, E> -> success(this.data)
        is FetchState.Error<T, E> -> error(this.error)
    }
}