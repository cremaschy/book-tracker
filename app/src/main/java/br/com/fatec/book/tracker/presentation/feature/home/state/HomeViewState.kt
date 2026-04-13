package br.com.fatec.book.tracker.presentation.feature.home.state

import br.com.fatec.book.tracker.presentation.core.state.UiState
import br.com.fatec.book.tracker.domain.model.Home

data class HomeViewState(
    val homeState: UiState<Home> = UiState.Loading
)
