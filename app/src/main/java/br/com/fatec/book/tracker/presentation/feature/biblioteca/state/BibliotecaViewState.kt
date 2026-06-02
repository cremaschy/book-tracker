package br.com.fatec.book.tracker.presentation.feature.biblioteca.state

import br.com.fatec.book.tracker.domain.model.livro.Livro

data class BibliotecaViewState(
    val livros: List<Livro> = emptyList(),
)
