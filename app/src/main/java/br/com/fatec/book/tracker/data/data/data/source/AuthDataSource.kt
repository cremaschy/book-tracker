package br.com.fatec.book.tracker.data.data.data.source

import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    interface Local {
        val token: Flow<Token?>
        suspend fun auth(): Token?
        suspend fun saveToken(token: Token?)
    }
}
