package br.com.fatec.book.tracker.presentation.feature.detalhe.state

import br.com.fatec.book.tracker.domain.model.livro.Livro

interface DetalheIntent {
    data class Initialize(val livro: Livro) : DetalheIntent
    data object OnBackClicked : DetalheIntent
}
