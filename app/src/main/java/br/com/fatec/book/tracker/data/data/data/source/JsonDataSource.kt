package br.com.fatec.book.tracker.data.data.data.source

import br.com.fatec.book.tracker.domain.model.Post

interface JsonDataSource {
    interface Remote : JsonDataSource {
        suspend fun getPosts(): List<Post>
    }

    interface Local : JsonDataSource {
        suspend fun getCachedPosts(): List<Post>
        suspend fun savePosts(posts: List<Post>)
    }
}
