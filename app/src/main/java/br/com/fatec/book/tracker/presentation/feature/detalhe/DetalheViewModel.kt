package br.com.fatec.book.tracker.presentation.feature.detalhe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewEvent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheIntent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewEvent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalheViewModel : ViewModel() {
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
        }
    }

    private fun initialize(livro: Livro) {
        _state.value = _state.value.copy(livro = livro)
    }
}
