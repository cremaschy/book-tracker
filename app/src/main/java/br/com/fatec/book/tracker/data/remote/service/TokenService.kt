package br.com.fatec.book.tracker.data.remote.service

import br.com.fatec.book.tracker.domain.model.Token

interface TokenService {
    suspend fun auth(email: String, senha: String): Token
}