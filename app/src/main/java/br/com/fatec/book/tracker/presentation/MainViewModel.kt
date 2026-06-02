package br.com.fatec.book.tracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel(
    private val tokenRepository: TokenRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state = _state.asStateFlow()

    private var wasLogged = false

    init {
        checkUserLogged()
    }

    private fun checkUserLogged() {
        viewModelScope.launch {
            tokenRepository.token.collect { token ->
                if (token != null) {
                    wasLogged = true
                    _state.value = MainState.Logged
                } else if (wasLogged) {
                    wasLogged = false
                    _state.value = MainState.SessionExpired
                } else {
                    _state.value = MainState.Login
                }
            }
        }
    }

    fun onSessionExpiredConfirmed() {
        _state.value = MainState.Login
    }

    fun onLogout() {
        viewModelScope.launch {
            wasLogged = false
            tokenRepository.logout()
            _state.value = MainState.Login
        }
    }

    fun navigateToLogin() {
        _state.value = MainState.Login
    }

    fun navigateToRegister() {
        _state.value = MainState.Register
    }

}

sealed interface MainState {
    object Loading : MainState
    object Login : MainState
    object Register : MainState
    object Logged : MainState
    object SessionExpired : MainState
}
