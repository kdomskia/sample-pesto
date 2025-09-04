package io.kdomskia.sample.pesto.data.http

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json

private val BASE_URL: String = TODO("Configure server address here")
// - For local development, point to your server’s IP address:
//private val BASE_URL = "http://192.168.0.1:8080"
// - Or use the public Kdomskia Pesto API:
//private val BASE_URL = "https://api.pesto.kdomskia.io"

fun provideHttpClient() = HttpClient {
    defaultRequest {
        url(BASE_URL)
        headers.appendIfNameAbsent(HttpHeaders.ContentType, ContentType.Application.Json.toString())
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
    install(Resources)
}