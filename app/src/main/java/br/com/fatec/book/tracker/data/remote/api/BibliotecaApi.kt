package br.com.fatec.book.tracker.data.remote.api

import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.data.remote.service.BibliotecaService
import br.com.fatec.book.tracker.domain.model.livro.Livro
import io.ktor.client.HttpClient

class BibliotecaApi(
    private val httpClient: HttpClient,
) : BibliotecaService {
    override suspend fun postLivro(livro: Livro): LivroDto {
        TODO("Not yet implemented")
    }

    override suspend fun getLivros(userId: String): List<LivroDto> {
        TODO("Not yet implemented")
    }

    override suspend fun pathLivro(livroId: Int): LivroDto {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLivro(livroId: Int) {
        TODO("Not yet implemented")
    }
}
