package br.com.fatec.book.tracker.data.data.data.source

import br.com.fatec.book.tracker.data.remote.dto.LivroDto

interface BibliotecaDataSource {
    interface Remote : BibliotecaDataSource {
        suspend fun postLivro(
            titulo: String,
            sinopse: String,
            totalPaginas: Int,
            autor: String,
            idSituacao: Int,
            userId: String
        ): LivroDto
        suspend fun getLivros(userId: String): List<LivroDto>
        suspend fun pathLivro(livroId: Int, paginasLidas: Int): LivroDto
        suspend fun deleteLivro(livroId: Int)
    }
}
