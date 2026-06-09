package br.com.fatec.book.tracker.presentation.feature.biblioteca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaIntent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewEvent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BibliotecaViewModel : ViewModel() {
    private val _state = MutableStateFlow(BibliotecaViewState())
    val state = _state.asStateFlow()

    private val _viewEvent = MutableSharedFlow<BibliotecaViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun onIntent(intent: BibliotecaIntent) {
        when (intent) {
            BibliotecaIntent.Load -> {}
            BibliotecaIntent.RetryLoad -> {}
            is BibliotecaIntent.OnFilterSelected -> {}
            is BibliotecaIntent.OnLivroClicked -> {}
            BibliotecaIntent.OnBackClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(BibliotecaViewEvent.NavigateToHome)
                }
            }
        }
    }
}
