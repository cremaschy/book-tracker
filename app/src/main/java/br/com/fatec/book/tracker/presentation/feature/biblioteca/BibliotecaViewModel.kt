package br.com.fatec.book.tracker.presentation.feature.biblioteca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.repository.BibliotecaRepository
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaIntent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewEvent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewState
import br.com.fatec.book.tracker.ui.components.ScreenType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BibliotecaViewModel(
    private val bibliotecaRepository: BibliotecaRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BibliotecaViewState())
    val state = _state.asStateFlow()

    private val _viewEvent = MutableSharedFlow<BibliotecaViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    init {
        getLivros()
    }

    fun onIntent(intent: BibliotecaIntent) {
        when (intent) {
            BibliotecaIntent.Load -> {
                getLivros()
            }
            BibliotecaIntent.RetryLoad -> {
                getLivros()
            }
            is BibliotecaIntent.OnFilterSelected -> {
                _state.value = _state.value.copy(filterSelected = intent.status)
            }
            is BibliotecaIntent.OnLivroClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(BibliotecaViewEvent.NavigateToDetalhe(intent.livro))
                }
            }
            BibliotecaIntent.OnBackClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(BibliotecaViewEvent.NavigateToHome)
                }
            }
        }
    }

    private fun getLivros() {
        viewModelScope.launch {
            _state.value = _state.value.copy(screenType = ScreenType.Loading)
            runCatching {
                val tokenData = tokenRepository.getToken()
                bibliotecaRepository.getLivros(tokenData.userId)
            }.onSuccess { livros ->
                _state.value = _state.value.copy(
                    livros = livros,
                    screenType = ScreenType.Content
                )
            }.onFailure {
                _state.value = _state.value.copy(
                    screenType = ScreenType.Error,
                    error = it.message
                )
            }
        }
    }
}
