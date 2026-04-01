package br.com.fatec.book.tracker.data.remote.service

import br.com.fatec.book.tracker.domain.model.Post

interface JsonService {
    suspend fun getPosts(): List<Post>
}
