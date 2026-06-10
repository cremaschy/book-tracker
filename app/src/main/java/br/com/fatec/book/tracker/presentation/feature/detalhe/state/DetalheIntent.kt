package br.com.fatec.book.tracker.presentation.feature.detalhe.state

import br.com.fatec.book.tracker.domain.model.livro.Livro

interface DetalheIntent {
    data class Initialize(val livro: Livro) : DetalheIntent
    data object OnBackClicked : DetalheIntent
    data object OnDeleteClicked : DetalheIntent
    data object OnAtualizarProgressoClicked : DetalheIntent
    data class OnSalvarProgresso(val paginas: Int) : DetalheIntent
}
