package br.com.fatec.book.tracker.domain.repository

import br.com.fatec.book.tracker.domain.model.livro.Livro

interface BibliotecaRepository {
    suspend fun postLivro(
        titulo: String,
        sinopse: String,
        totalPaginas: Int,
        autor: String,
        idSituacao: Int,
        userId: String
    )

    suspend fun getLivros(userId: String): List<Livro>
    suspend fun pathLivro(livroId: Int, paginasLidas: Int)
    suspend fun deleteLivro(livroId: Int)
}
