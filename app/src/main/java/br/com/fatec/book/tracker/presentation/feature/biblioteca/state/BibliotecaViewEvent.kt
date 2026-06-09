package br.com.fatec.book.tracker.presentation.feature.biblioteca.state

import br.com.fatec.book.tracker.domain.model.livro.Livro

interface BibliotecaViewEvent {
 data object NavigateToHome : BibliotecaViewEvent
    data class NavigateToDetalhe(val livro: Livro) : BibliotecaViewEvent
}
