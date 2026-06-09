package br.com.fatec.book.tracker.data.data.repository

import br.com.fatec.book.tracker.data.data.data.source.TokenDataSource
import br.com.fatec.book.tracker.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val localDataSource: TokenDataSource.Local,
) : AuthRepository {
    override suspend fun getToken(): String {
        return localDataSource.getToken()?.token
            ?: error("Erro ao buscar token")
    }

    override suspend fun clearSession() {
        localDataSource.saveToken(null)
    }
}
