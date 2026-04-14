package br.com.fatec.book.tracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state = _state.asStateFlow()

   init {
       checkUserLogged()
   }

    private fun checkUserLogged() {
        viewModelScope.launch {
            delay(2.seconds)
            authRepository.token.collect { token ->
                when {
                    token != null -> {
                        _state.value = MainState.Logged
                    }
                    else -> {
                        _state.value = MainState.Login
                    }
                }
            }
        }
    }

    fun onLogin() {
        _state.value = MainState.Login
    }

    fun onRegister() {
        _state.value = MainState.Register
    }

    fun onLoginSucess() {
        viewModelScope.launch {
            runCatching {
                authRepository.saveToken()
            }.fold(
                onSuccess = {
                    _state.value = MainState.Logged
                },
                onFailure = {
                    _state.value = MainState.Login
                }
            )
        }
    }

    fun onRegisterSucess() {
        viewModelScope.launch {
            runCatching {
                authRepository.saveToken()
            }.fold(
                onSuccess = {
                    _state.value = MainState.Logged
                },
                onFailure = {
                    _state.value = MainState.Login
                }
            )
        }
    }
}

sealed interface MainState {
    object Loading : MainState
    object Login : MainState
    object Register : MainState
    object Logged : MainState
}
