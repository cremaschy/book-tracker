package br.com.fatec.book.tracker.presentation.feature.placeholder.list.state

sealed interface PlaceholderIntent {
    data object LoadPosts : PlaceholderIntent
    data object RetryLoadPosts : PlaceholderIntent
    data class OnPostClicked(val postId: Int) : PlaceholderIntent
}