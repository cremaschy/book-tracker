package br.com.fatec.book.tracker.domain.repository

import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    val token: Flow<Token?>

    suspend fun login(email: String, senha: String)
    suspend fun getToken(): Token
    suspend fun logout()
}
