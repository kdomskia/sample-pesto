package io.kdomskia.sample.pesto.domain.model.fetch

sealed interface FetchResult<T, E> {

    class Success<T, E>(
        val data: T
    ) : FetchResult<T, E>

    class Error<T, E>(
        val error: E
    ) : FetchResult<T, E>

}