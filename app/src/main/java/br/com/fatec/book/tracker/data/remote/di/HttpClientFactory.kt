package br.com.fatec.book.tracker.data.remote.di

import br.com.fatec.book.tracker.domain.repository.AuthRepository
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "http://localhost:5092/swagger/v1/swagger.json/"
fun createHttpClient(engine: HttpClientEngine, repository: AuthRepository) = HttpClient(engine) {
    applyDefaults(repository)
}

internal fun HttpClientConfig<*>.applyDefaults(repository: AuthRepository) {
    expectSuccess = true
    defaultRequest {
        url(BASE_URL)
    }

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            }
        )
    }

    install(Auth) {
        bearer {
            loadTokens {
                val token = runCatching { repository.getToken() }.getOrNull()
                if (token != null) BearerTokens(token, "") else null
            }
        }
    }

    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, _ ->
            if (cause is ClientRequestException &&
                cause.response.status == HttpStatusCode.Unauthorized
            ) {
                repository.clearSession()
            }
        }
    }

    install(Logging) {
        logger = KtorLogger()
        level = LogLevel.ALL
    }
}
