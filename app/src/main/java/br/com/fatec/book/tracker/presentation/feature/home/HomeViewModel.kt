package br.com.fatec.book.tracker.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.presentation.MainState
import br.com.fatec.book.tracker.presentation.feature.home.state.HomeIntent
import br.com.fatec.book.tracker.presentation.feature.home.state.HomeViewEvent
import br.com.fatec.book.tracker.presentation.feature.home.state.HomeViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private val _viewEvent = MutableSharedFlow<HomeViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnAdicionarClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(HomeViewEvent.NavigateToAdicionar)
                }
            }

            HomeIntent.OnBibliotecaClicked -> {
                viewModelScope.launch {
                    _viewEvent.emit(HomeViewEvent.NavigateToBiblioteca)
                }
            }
        }
    }
}
