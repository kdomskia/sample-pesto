package io.kdomskia.sample.pesto.ui.model.fetch

sealed interface FetchState<T, E> {

    class Idle<T, E> : FetchState<T, E>

    class Loading<T, E> : FetchState<T, E>

    class Success<T, E>(
        val data: T
    ) : FetchState<T, E>

    class Error<T, E>(
        val error: E
    ) : FetchState<T, E>

}