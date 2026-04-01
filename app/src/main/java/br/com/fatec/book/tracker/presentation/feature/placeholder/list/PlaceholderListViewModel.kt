package br.com.fatec.book.tracker.presentation.feature.placeholder.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fatec.book.tracker.domain.repository.JsonRepository
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderIntent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewEvent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaceholderListViewModel(
    private val repository: JsonRepository,
) : ViewModel() {
    private val _viewState = MutableStateFlow(PlaceholderViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<PlaceholderViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    fun onIntent(intent: PlaceholderIntent) {
        when (intent) {
            is PlaceholderIntent.OnPostClicked -> onEvent {
                PlaceholderViewEvent.NavigateToComments(postId = intent.postId)
            }

            PlaceholderIntent.LoadPosts -> {
                loadPosts()
            }

            PlaceholderIntent.RetryLoadPosts -> {
                _viewState.update {
                    it.loading()
                }
                loadPosts()
            }
        }
    }

    // varios metodos privados
    private fun loadPosts() {
        viewModelScope.launch {
            runCatching {
                repository.getPosts()
            }.onSuccess { posts ->
                _viewState.update {
                    it.success(posts = posts)
                }
            }.onFailure {
                _viewState.update {
                    it.error()
                }
            }
        }
    }

    private fun onEvent(event: () -> PlaceholderViewEvent) {
        viewModelScope.launch {
            _viewEvent.emit(event())
        }
    }
}
