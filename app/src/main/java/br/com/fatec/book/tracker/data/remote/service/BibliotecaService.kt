package br.com.fatec.book.tracker.data.remote.service

import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.domain.model.livro.Livro

interface BibliotecaService {
    suspend fun postLivro(livro: Livro): LivroDto
    suspend fun getLivros(userId: String): List<LivroDto>
    suspend fun pathLivro(livroId: Int): LivroDto
    suspend fun deleteLivro(livroId: Int)
}
