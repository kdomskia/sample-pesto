package io.kdomskia.sample.pesto.ui.extension

import io.kdomskia.sample.pesto.domain.model.fetch.FetchResult
import io.kdomskia.sample.pesto.ui.model.fetch.FetchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

suspend fun <T, E> Flow<FetchResult<T, E>>.collectAsFetchState(
    onResult: (FetchState<T, E>) -> Unit
) {
    onResult(FetchState.Loading())
    withContext(Dispatchers.Default) {
        collect {
            when (it) {
                is FetchResult.Success -> {
                    onResult(FetchState.Success(it.data))
                }

                is FetchResult.Error -> {
                    onResult(FetchState.Error(it.error))
                }
            }
        }
    }
}