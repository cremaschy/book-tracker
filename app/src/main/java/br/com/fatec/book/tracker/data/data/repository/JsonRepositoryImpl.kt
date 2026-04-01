package br.com.fatec.book.tracker.data.data.repository

import br.com.fatec.book.tracker.data.data.data.source.JsonDataSource
import br.com.fatec.book.tracker.domain.model.Post
import br.com.fatec.book.tracker.domain.repository.JsonRepository

class JsonRepositoryImpl(
    private val remoteDataSource: JsonDataSource.Remote,
    private val localDataSource: JsonDataSource.Local,
) : JsonRepository {
    override suspend fun getPosts(): List<Post> {
        val cachedPosts = localDataSource.getCachedPosts()
        return cachedPosts.ifEmpty {
            val posts = remoteDataSource.getPosts()
            localDataSource.savePosts(posts)
            posts
        }
    }
}
