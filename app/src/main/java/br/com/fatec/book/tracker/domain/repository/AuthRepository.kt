package br.com.fatec.book.tracker.domain.repository

import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getToken(): String
    suspend fun clearSession()
}
