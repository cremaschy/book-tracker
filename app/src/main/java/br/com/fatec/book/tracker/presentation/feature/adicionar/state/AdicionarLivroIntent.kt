package br.com.fatec.book.tracker.presentation.feature.adicionar.state

interface AdicionarLivroIntent {
    data object OnBackClicked : AdicionarLivroIntent
    data object OnRetryClicked : AdicionarLivroIntent
    data class OnTituloChange(val titulo: String) : AdicionarLivroIntent
    data class OnSinopseChange(val sinopse: String) : AdicionarLivroIntent
    data class OnTotalPaginasChange(val totalPaginas: String) : AdicionarLivroIntent
    data class OnAutorChange(val autor: String) : AdicionarLivroIntent
    data class OnSituacaoChange(val idSituacao: Int) : AdicionarLivroIntent
    object OnAdicionarClicked : AdicionarLivroIntent
}