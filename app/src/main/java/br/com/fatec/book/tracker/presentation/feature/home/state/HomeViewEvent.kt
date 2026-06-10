package br.com.fatec.book.tracker.presentation.feature.home.state

interface HomeViewEvent {
    data object NavigateToLogin : HomeViewEvent
    data object NavigateToAdicionar : HomeViewEvent
    data object NavigateToBiblioteca : HomeViewEvent
}
