package br.com.fatec.book.tracker.data.remote.api

import br.com.fatec.book.tracker.data.remote.service.TokenService
import br.com.fatec.book.tracker.domain.model.Token
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.Serializable

class TokenApi(
    private val httpClient: HttpClient,
) : TokenService {
    override suspend fun auth(email: String, senha: String): Token {
        return httpClient.post {
            url { path("login") }
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    email = email,
                    senha = senha,
                ),
            )
        }.body<Token>()
    }
}

@Serializable
data class LoginRequest(
    val email: String,
    val senha: String,
)
