package br.com.fatec.book.tracker.data.remote.data.source

import br.com.fatec.book.tracker.data.data.data.source.BibliotecaDataSource
import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.data.remote.service.BibliotecaService

data class BibliotecaDataSourceRemote(
    private val bibliotecaService: BibliotecaService,
) : BibliotecaDataSource.Remote {
    override suspend fun postLivro(
        titulo: String,
        sinopse: String,
        totalPaginas: Int,
        autor: String,
        idSituacao: Int,
        userId: String
    ): LivroDto {
        return bibliotecaService.postLivro(
            titulo = titulo,
            sinopse = sinopse,
            totalPaginas = totalPaginas,
            autor = autor,
            idSituacao = idSituacao,
            userId = userId
        )
    }

    override suspend fun getLivros(userId: String): List<LivroDto> {
        return bibliotecaService.getLivros(userId)
    }

    override suspend fun pathLivro(livroId: Int, paginasLidas: Int): LivroDto {
        return bibliotecaService.pathLivro(livroId, paginasLidas)
    }

    override suspend fun deleteLivro(livroId: Int) {
        bibliotecaService.deleteLivro(livroId)
    }
}
