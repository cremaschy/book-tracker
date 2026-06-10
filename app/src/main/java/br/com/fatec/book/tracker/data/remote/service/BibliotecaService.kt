package br.com.fatec.book.tracker.data.remote.service

import br.com.fatec.book.tracker.data.remote.dto.LivroDto

interface BibliotecaService {
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
