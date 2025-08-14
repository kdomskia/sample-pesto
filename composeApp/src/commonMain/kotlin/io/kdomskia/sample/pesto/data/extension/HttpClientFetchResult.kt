package io.kdomskia.sample.pesto.data.extension

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.kdomskia.sample.pesto.domain.model.fetch.FetchResult
import io.kdomskia.sample.pesto.domain.model.fetch.FetchError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <reified T> HttpClient.fetchResult(
    crossinline block: suspend HttpClient.() -> HttpResponse
): Flow<FetchResult<T, FetchError>> = fetchResult<T, FetchError>(
    block = block,
    mapError = { FetchError() }
)

inline fun <reified T, reified E> HttpClient.fetchResult(
    crossinline block: suspend HttpClient.() -> HttpResponse,
    crossinline mapError: (Throwable) -> E
): Flow<FetchResult<T, E>> {
    val httpClient = this
    return flow {
        try {
            emit(
                FetchResult.Success(
                    data = block(httpClient).body()
                )
            )
        } catch (e: Throwable) {
            emit(
                FetchResult.Error(
                    error = mapError(e)
                )
            )
        }
    }
}