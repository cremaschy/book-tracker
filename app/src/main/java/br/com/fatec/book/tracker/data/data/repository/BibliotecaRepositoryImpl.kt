package br.com.fatec.book.tracker.data.data.repository

import br.com.fatec.book.tracker.data.data.data.source.BibliotecaDataSource
import br.com.fatec.book.tracker.data.data.mapper.toDomain
import br.com.fatec.book.tracker.data.remote.service.BibliotecaService
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.domain.repository.BibliotecaRepository

data class BibliotecaRepositoryImpl(
    private val bibliotecaService: BibliotecaService,
    private val bibliotecaDataSource: BibliotecaDataSource.Remote
) : BibliotecaRepository {
    override suspend fun postLivro(
        titulo: String,
        sinopse: String,
        totalPaginas: Int,
        autor: String,
        idSituacao: Int,
        userId: String
    ) {
        bibliotecaDataSource.postLivro(
            titulo = titulo,
            sinopse = sinopse,
            totalPaginas = totalPaginas,
            autor = autor,
            idSituacao = idSituacao,
            userId = userId
        )
    }

    override suspend fun getLivros(userId: String): List<Livro> {
        return bibliotecaDataSource.getLivros(userId).map { it.toDomain() }
    }

    override suspend fun pathLivro(livroId: Int, paginasLidas: Int) {
        bibliotecaDataSource.pathLivro(livroId, paginasLidas)
    }

    override suspend fun deleteLivro(livroId: Int) {
        bibliotecaDataSource.deleteLivro(livroId)
    }
}
