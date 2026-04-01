package br.com.fatec.book.tracker.domain.repository

import br.com.fatec.book.tracker.domain.model.Post

interface JsonRepository{
    suspend fun getPosts(): List<Post>
}
