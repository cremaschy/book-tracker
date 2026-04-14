package br.com.fatec.book.tracker.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Routes : NavKey {
    @Serializable
    data object Login : Routes

    @Serializable
    data object Register : Routes

    @Serializable
    data object Home : Routes

    @Serializable
    data object PlaceholderList : Routes

    @Serializable
    data class CommentList(val postId: Int) : Routes
}
