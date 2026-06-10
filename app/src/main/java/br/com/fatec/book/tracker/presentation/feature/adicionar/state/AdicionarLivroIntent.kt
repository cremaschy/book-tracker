package br.com.fatec.book.tracker.presentation.feature.adicionar.state

import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus

interface AdicionarLivroIntent {
    data object OnBackClicked : AdicionarLivroIntent
    data object OnRetryClicked : AdicionarLivroIntent
    data class OnTituloChange(val titulo: String) : AdicionarLivroIntent
    data class OnSinopseChange(val sinopse: String) : AdicionarLivroIntent
    data class OnTotalPaginasChange(val totalPaginas: String) : AdicionarLivroIntent
    data class OnAutorChange(val autor: String) : AdicionarLivroIntent
    data object OnSituacaoClicked : AdicionarLivroIntent
    data class OnSituacaoChange(val status: ReadingStatus) : AdicionarLivroIntent
    data object OnDismissStatusBottomSheet : AdicionarLivroIntent
    object OnAdicionarClicked : AdicionarLivroIntent
}