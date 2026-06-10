package br.com.fatec.book.tracker.presentation.feature.detalhe.state

import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.ui.components.ScreenType

data class DetalheViewState(
    val livro: Livro = Livro(
        id = 0,
        titulo = "",
        sinopse = "",
        totalPaginas = 0,
        autor = "0",
        idSituacao = 0,
        paginasLidas = 0,
    ),
    val screenType: ScreenType = ScreenType.Content,
    val error: String? = null
)
