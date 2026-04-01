package br.com.fatec.book.tracker.data.remote.api

import br.com.fatec.book.tracker.data.remote.service.JsonService
import br.com.fatec.book.tracker.domain.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class JsonApi(
    private val httpClient: HttpClient,
) : JsonService {
    override suspend fun getPosts(): List<Post> {
        return httpClient.get {
            url {
                path("posts")
            }
        }.body<List<Post>>()
    }
}