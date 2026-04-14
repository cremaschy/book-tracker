package br.com.fatec.book.tracker.domain.repository

import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val token: Flow<Token?>

    suspend fun auth(): Token
    suspend fun logout()
    suspend fun saveToken()
}
