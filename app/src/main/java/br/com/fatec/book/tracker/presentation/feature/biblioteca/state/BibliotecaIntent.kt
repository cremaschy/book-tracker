package br.com.fatec.book.tracker.presentation.feature.biblioteca.state

import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus

interface BibliotecaIntent {
    data object OnBackClicked : BibliotecaIntent
    data object Load : BibliotecaIntent
    data object RetryLoad : BibliotecaIntent
    data class OnFilterSelected(val status: ReadingStatus) : BibliotecaIntent
    data class OnLivroClicked(val livro: Livro) : BibliotecaIntent
}