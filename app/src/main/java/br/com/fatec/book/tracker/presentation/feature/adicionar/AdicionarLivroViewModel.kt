package br.com.fatec.book.tracker.presentation.feature.adicionar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.repository.BibliotecaRepository
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroIntent
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewEvent
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewState
import br.com.fatec.book.tracker.ui.components.ScreenType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdicionarLivroViewModel(
    private val bibliotecaRepository: BibliotecaRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AdicionarLivroViewState())
    val state = _state.asStateFlow()

    private val _viewEvent = MutableSharedFlow<AdicionarLivroViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun onIntent(intent: AdicionarLivroIntent) {
        when (intent) {
            is AdicionarLivroIntent.OnTituloChange -> {
                _state.value = _state.value.copy(titulo = intent.titulo)
            }

            is AdicionarLivroIntent.OnSinopseChange -> {
                _state.value = _state.value.copy(sinopse = intent.sinopse)
            }

            is AdicionarLivroIntent.OnTotalPaginasChange -> {
                _state.value = _state.value.copy(totalPaginas = intent.totalPaginas)
            }

            is AdicionarLivroIntent.OnAutorChange -> {
                _state.value = _state.value.copy(autor = intent.autor)
            }

            AdicionarLivroIntent.OnBackClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(AdicionarLivroViewEvent.NavigateToHome)
                }
            }

            AdicionarLivroIntent.OnAdicionarClicked -> {
                postLivro()
            }

            AdicionarLivroIntent.OnRetryClicked -> {
                _state.value = _state.value.copy(screenType = ScreenType.Content)
            }

            is AdicionarLivroIntent.OnSituacaoClicked -> {
                _state.value = _state.value.copy(showStatusBottomSheet = true)
            }

            is AdicionarLivroIntent.OnDismissStatusBottomSheet -> {
                _state.value = _state.value.copy(showStatusBottomSheet = false)
            }

            is AdicionarLivroIntent.OnSituacaoChange -> {
                _state.value = _state.value.copy(
                    readingStatus = intent.status,
                    showStatusBottomSheet = false
                )
            }
        }
    }

    private fun postLivro() {
        viewModelScope.launch {
            _state.value = _state.value.copy(screenType = ScreenType.Loading)

            runCatching {
                val tokenData = tokenRepository.getToken()
                bibliotecaRepository.postLivro(
                    titulo = state.value.titulo,
                    sinopse = state.value.sinopse,
                    totalPaginas = state.value.totalPaginas.toIntOrNull() ?: 0,
                    autor = state.value.autor,
                    idSituacao = state.value.readingStatus.id,
                    userId = tokenData.userId
                )
            }.onSuccess {
                _viewEvent.emit(AdicionarLivroViewEvent.NavigateToHome)
            }.onFailure {
                _state.value = _state.value.copy(screenType = ScreenType.Error)
            }
        }
    }
}
