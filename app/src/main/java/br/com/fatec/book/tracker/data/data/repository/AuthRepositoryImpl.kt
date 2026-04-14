package br.com.fatec.book.tracker.data.data.repository

import br.com.fatec.book.tracker.data.data.data.source.AuthDataSource
import br.com.fatec.book.tracker.domain.model.Token
import br.com.fatec.book.tracker.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val localDataSource: AuthDataSource.Local,
) : AuthRepository {
    override val token: Flow<Token?>
        get() = localDataSource.token

    override suspend fun auth() =
        localDataSource.auth() ?: error("Erro ao buscar usuário")

    override suspend fun logout() {
        localDataSource.saveToken(null)
    }

    override suspend fun saveToken() {
        val token = Token("token_mock_123456")
        localDataSource.saveToken(token)
    }
}
