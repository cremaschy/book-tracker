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
    override suspend fun postLivro(livro: Livro): Livro {
        return bibliotecaDataSource.postLivro(livro).toDomain()
    }

    override suspend fun getLivros(userId: String): List<Livro> {
        return bibliotecaDataSource.getLivros(userId).map { it.toDomain() }
    }

    override suspend fun pathLivro(livroId: Int): Livro {
        return bibliotecaDataSource.pathLivro(livroId).toDomain()
    }

    override suspend fun deleteLivro(livroId: Int) {
        bibliotecaDataSource.deleteLivro(livroId)
    }
}
