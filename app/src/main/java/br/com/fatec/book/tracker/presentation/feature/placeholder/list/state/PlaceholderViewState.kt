package br.com.fatec.book.tracker.presentation.feature.placeholder.list.state

import br.com.fatec.book.tracker.domain.model.Post

data class PlaceholderViewState(
    val screenType: ScreenType = ScreenType.Loading,
    val posts: List<Post> = emptyList(),
) {
    fun success(posts: List<Post>) = copy(posts = posts, screenType = ScreenType.Content)
    fun error() = copy(screenType = ScreenType.Error)
    fun loading() = copy(screenType = ScreenType.Loading)
}