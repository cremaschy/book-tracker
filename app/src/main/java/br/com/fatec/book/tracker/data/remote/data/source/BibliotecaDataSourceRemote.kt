package br.com.fatec.book.tracker.data.remote.data.source

import br.com.fatec.book.tracker.data.data.data.source.BibliotecaDataSource
import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.data.remote.service.BibliotecaService
import br.com.fatec.book.tracker.domain.model.livro.Livro

data class BibliotecaDataSourceRemote(
    private val bibliotecaService: BibliotecaService,
) : BibliotecaDataSource.Remote {
    override suspend fun postLivro(livro: Livro): LivroDto {
        return bibliotecaService.postLivro(livro)
    }

    override suspend fun getLivros(userId: String): List<LivroDto> {
        return bibliotecaService.getLivros(userId)
    }

    override suspend fun pathLivro(livroId: Int): LivroDto {
        return bibliotecaService.pathLivro(livroId)
    }

    override suspend fun deleteLivro(livroId: Int) {
        bibliotecaService.deleteLivro(livroId)
    }
}
