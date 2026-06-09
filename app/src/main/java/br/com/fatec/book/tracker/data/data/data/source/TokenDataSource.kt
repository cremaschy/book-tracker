package br.com.fatec.book.tracker.data.data.data.source

import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface TokenDataSource {
    interface Remote : TokenDataSource {
        suspend fun auth(
            email: String,
            senha: String
        ): Token
    }

    interface Local : TokenDataSource {
        val token: Flow<Token?>
        suspend fun getToken(): Token?
        suspend fun saveToken(token: Token?)
    }
}