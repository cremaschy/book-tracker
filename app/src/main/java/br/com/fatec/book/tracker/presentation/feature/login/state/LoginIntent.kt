package br.com.fatec.book.tracker.presentation.feature.login.state

interface LoginIntent {
    data class OnEmailChanged(val email: String) : LoginIntent
    data class OnPasswordChanged(val senha: String) : LoginIntent
    object OnLoginClicked : LoginIntent
    object OnRetryClicked : LoginIntent
}
