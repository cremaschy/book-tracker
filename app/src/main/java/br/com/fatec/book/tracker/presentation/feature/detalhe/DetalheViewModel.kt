package br.com.fatec.book.tracker.presentation.feature.detalhe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.domain.repository.BibliotecaRepository
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheIntent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewEvent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewState
import br.com.fatec.book.tracker.ui.components.ScreenType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalheViewModel(
    private val bibliotecaRepository: BibliotecaRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetalheViewState())
    val state = _state.asStateFlow()

    private val _viewEvent = MutableSharedFlow<DetalheViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun onIntent(intent: DetalheIntent) {
        when (intent) {
            is DetalheIntent.Initialize -> {
                initialize(intent.livro)
            }

            DetalheIntent.OnBackClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(DetalheViewEvent.NavigateToBiblioteca)
                }
            }

            DetalheIntent.OnDeleteClicked -> {
                deleteLivro()
            }

            is DetalheIntent.OnSalvarProgresso -> {
                atualizarProgresso(intent.paginas)
            }
        }
    }

    private fun initialize(livro: Livro) {
        _state.value = _state.value.copy(livro = livro)
    }

    private fun deleteLivro() {
        val livroId = state.value.livro.id ?: return
        viewModelScope.launch {
            runCatching {
                bibliotecaRepository.deleteLivro(livroId)
            }.onSuccess {
                _viewEvent.emit(DetalheViewEvent.NavigateToBiblioteca)
            }.onFailure {
                _state.value = _state.value.copy(
                    screenType = ScreenType.Error,
                    error = it.message
                )
            }
        }
    }

    private fun atualizarProgresso(paginas: Int) {
        val livroId = state.value.livro.id ?: return
        viewModelScope.launch {
            runCatching {
                bibliotecaRepository.pathLivro(livroId, paginas)
            }.onSuccess {
                _state.value = _state.value.copy(
                    livro = _state.value.livro.copy(paginasLidas = paginas)
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
