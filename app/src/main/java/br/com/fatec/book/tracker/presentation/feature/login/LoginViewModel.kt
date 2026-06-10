package br.com.fatec.book.tracker.presentation.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginIntent
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginViewEvent
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginViewState
import br.com.fatec.book.tracker.ui.components.ScreenType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: TokenRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    private val _viewEvent = MutableSharedFlow<LoginViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnEmailChanged -> {
                _state.value = _state.value.copy(email = intent.email)
            }

            is LoginIntent.OnPasswordChanged -> {
                _state.value = _state.value.copy(senha = intent.senha)
            }

            LoginIntent.OnLoginClicked -> {
                login()
            }

            LoginIntent.OnRetryClicked -> {
                _state.value = _state.value.copy(screenType = ScreenType.Content)
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.value = _state.value.copy(screenType = ScreenType.Loading)

            runCatching {
                repository.login(
                    email = _state.value.email,
                    senha = _state.value.senha,
                )
                _state.value = _state.value.copy(screenType = ScreenType.Content)
            }.onFailure { throwable ->
                _state.update {
                    it.copy(
                        screenType = ScreenType.Error,
                        error = throwable.message ?: "Erro desconhecido",
                    )
                }
            }
        }
    }
}
