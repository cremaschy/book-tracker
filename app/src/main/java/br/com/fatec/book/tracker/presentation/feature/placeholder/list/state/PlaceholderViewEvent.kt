package br.com.fatec.book.tracker.presentation.feature.placeholder.list.state

sealed interface PlaceholderViewEvent {
    data class NavigateToComments(val postId: Int) : PlaceholderViewEvent
}