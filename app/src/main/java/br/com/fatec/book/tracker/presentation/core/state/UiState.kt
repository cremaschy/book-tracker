package br.com.fatec.book.tracker.presentation.core.state

sealed interface UiState<out T> {

    data object Loading : UiState<Nothing>

    data object Empty : UiState<Nothing>

    data class Success<T>(
        val data: T
    ) : UiState<T>

    data class Error(
        val message: String? = null
    ) : UiState<Nothing>
}