package br.com.fatec.book.tracker.presentation.feature.adicionar.state

import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType

data class AdicionarLivroViewState(
    val titulo: String = "",
    val sinopse: String = "",
    val totalPaginas: String = "",
    val autor: String = "",
    val screenType: ScreenType = ScreenType.Content,
) {
    val isEnabled: Boolean
        get() = titulo.isNotEmpty() && sinopse.isNotEmpty() && autor.isNotEmpty()
}
