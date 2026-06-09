package br.com.fatec.book.tracker.presentation.feature.home.state

import br.com.fatec.book.tracker.domain.model.Home
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType

data class HomeViewState(
    val home: Home = Home(
        nome = "Gustavo",
        ofensiva = 365,
    ),
    val screenType: ScreenType = ScreenType.Loading
)
