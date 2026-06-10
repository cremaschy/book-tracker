package br.com.fatec.book.tracker.presentation.feature.adicionar.state

import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus
import br.com.fatec.book.tracker.ui.components.ScreenType

data class AdicionarLivroViewState(
    val titulo: String = "",
    val sinopse: String = "",
    val totalPaginas: String = "",
    val autor: String = "",
    val readingStatus: ReadingStatus = ReadingStatus.WANT_TO_READ,
    val showStatusBottomSheet: Boolean = false,
    val screenType: ScreenType = ScreenType.Content,
) {
    val isEnabled: Boolean
        get() = titulo.isNotEmpty() && sinopse.isNotEmpty() && autor.isNotEmpty()
}
