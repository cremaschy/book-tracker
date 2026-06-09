package br.com.fatec.book.tracker.presentation.feature.home.state

interface HomeIntent {
    data object OnAdicionarClicked : HomeIntent
    data object OnBibliotecaClicked : HomeIntent
}
