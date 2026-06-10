package br.com.fatec.book.tracker.data.data.data.source

import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.domain.model.livro.Livro

interface BibliotecaDataSource {
    interface Remote : BibliotecaDataSource {
        suspend fun postLivro(livro: Livro): LivroDto
        suspend fun getLivros(userId: String): List<LivroDto>
        suspend fun pathLivro(livroId: Int): LivroDto
        suspend fun deleteLivro(livroId: Int)
    }
}
