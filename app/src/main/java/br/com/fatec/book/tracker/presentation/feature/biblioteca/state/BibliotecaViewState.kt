package br.com.fatec.book.tracker.presentation.feature.biblioteca.state

import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus
import br.com.fatec.book.tracker.ui.components.ScreenType

data class BibliotecaViewState(
    val error: String? = null,
    val livros: List<Livro> = emptyList(),
    val filterSelected: ReadingStatus? = null,
    val screenType: ScreenType = ScreenType.Loading,
)
