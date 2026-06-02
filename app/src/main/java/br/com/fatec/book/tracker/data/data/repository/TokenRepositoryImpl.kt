package br.com.fatec.book.tracker.data.data.repository

import br.com.fatec.book.tracker.data.data.data.source.TokenDataSource
import br.com.fatec.book.tracker.domain.model.Token
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class TokenRepositoryImpl(
    private val localDataSource: TokenDataSource.Local,
    private val remoteDataSource: TokenDataSource.Remote,
) : TokenRepository {
    override val token: Flow<Token?>
        get() = localDataSource.token

    override suspend fun login(email: String, senha: String) {
        val token = remoteDataSource.auth(email, senha)
        localDataSource.saveToken(token)
    }

    override suspend fun getToken() = localDataSource.getToken() ?: error("Erro ao buscar usuário")


    override suspend fun logout() {
        localDataSource.saveToken(null)
    }
}
