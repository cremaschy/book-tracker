package br.com.fatec.book.tracker.domain.repository

import br.com.fatec.book.tracker.domain.model.livro.Livro

interface BibliotecaRepository {
    suspend fun postLivro(livro: Livro): Livro
    suspend fun getLivros(userId: String): List<Livro>
    suspend fun pathLivro(livroId: Int): Livro
    suspend fun deleteLivro(livroId: Int)
}
