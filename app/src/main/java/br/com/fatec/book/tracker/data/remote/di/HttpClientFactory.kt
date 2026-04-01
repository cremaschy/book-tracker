package br.com.fatec.book.tracker.data.remote.di

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

fun createHttpClient() = HttpClient(CIO) {
    applyDefaults()
}

internal fun HttpClientConfig<*>.applyDefaults() {
    expectSuccess = true
    defaultRequest {
        url(BASE_URL)
    }

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(Logging) {
        logger = KtorLogger()
        level = LogLevel.ALL
    }
}