package br.com.fatec.book.tracker.data.remote.data.source

import br.com.fatec.book.tracker.data.data.data.source.JsonDataSource
import br.com.fatec.book.tracker.data.remote.service.JsonService
import br.com.fatec.book.tracker.domain.model.Post

class JsonDataSourceRemote(
    private val jsonService: JsonService,
) : JsonDataSource.Remote {
    override suspend fun getPosts(): List<Post> {
        return jsonService.getPosts()
    }
}