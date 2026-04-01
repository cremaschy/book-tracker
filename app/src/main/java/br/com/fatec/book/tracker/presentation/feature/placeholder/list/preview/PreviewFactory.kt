package br.com.fatec.book.tracker.presentation.feature.placeholder.list.preview

import br.com.fatec.book.tracker.domain.model.Post
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewState
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType

object PreviewFactory {
    fun placeholderViewState() = PlaceholderViewState(
        posts = posts(),
        screenType = ScreenType.Content,
    )

    private fun posts() = listOf(
        Post(
            userId = 1,
            id = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        ),
        Post(
            userId = 1,
            id = 5,
            title = "nesciunt quas odio",
            body = "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque",
        ),
        Post(
            userId = 4,
            id = 37,
            title = "provident vel ut sit ratione est",
            body = "debitis et eaque non officia sed nesciunt pariatur vel\nvoluptatem iste vero et ea\nnumquam aut expedita ipsum nulla in\nvoluptates omnis consequatur aut enim officiis in quam qui"
        ),
    )
}