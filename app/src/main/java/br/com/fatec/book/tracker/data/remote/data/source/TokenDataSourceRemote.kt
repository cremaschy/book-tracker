package br.com.fatec.book.tracker.data.remote.data.source

import br.com.fatec.book.tracker.data.data.data.source.TokenDataSource
import br.com.fatec.book.tracker.data.remote.service.TokenService
import br.com.fatec.book.tracker.domain.model.Token

class TokenDataSourceRemote(
    private val tokenService: TokenService,
) : TokenDataSource.Remote {
    override suspend fun auth(email: String, senha: String): Token {
        return tokenService.auth(email, senha)
    }
}
