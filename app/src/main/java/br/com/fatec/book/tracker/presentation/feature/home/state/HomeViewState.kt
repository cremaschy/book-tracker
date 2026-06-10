package br.com.fatec.book.tracker.presentation.feature.home.state

import br.com.fatec.book.tracker.ui.components.ScreenType

data class HomeViewState(
    val screenType: ScreenType = ScreenType.Content
)
