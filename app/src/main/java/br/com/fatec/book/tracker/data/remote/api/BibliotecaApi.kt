package br.com.fatec.book.tracker.data.remote.api

import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.data.remote.service.BibliotecaService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.Serializable

class BibliotecaApi(
    private val httpClient: HttpClient,
) : BibliotecaService {
    override suspend fun postLivro(
        titulo: String,
        sinopse: String,
        totalPaginas: Int,
        autor: String,
        idSituacao: Int,
        userId: String,
    ): LivroDto {
        return httpClient.post {
            url { path("livros") }
            contentType(ContentType.Application.Json)
            setBody(
                PostLivroRequest(
                    titulo = titulo,
                    sinopse = sinopse,
                    totalPaginas = totalPaginas,
                    autor = autor,
                    idSituacao = idSituacao,
                    userId = userId
                )
            )
        }.body<LivroDto>()
    }

    override suspend fun getLivros(userId: String): List<LivroDto> {
        return httpClient.get {
            url { 
                path("livros")
                parameters.append("userId", userId)
            }
        }.body<List<LivroDto>>()
    }

    override suspend fun pathLivro(livroId: Int, paginasLidas: Int): LivroDto {
        return httpClient.patch {
            url { path("livros", livroId.toString()) }
            contentType(ContentType.Application.Json)
            setBody(PathLivroRequest(paginasLidas = paginasLidas))
        }.body<LivroDto>()
    }

    override suspend fun deleteLivro(livroId: Int) {
        httpClient.delete {
            url { path("livros", livroId.toString()) }
        }
    }
}

@Serializable
data class PostLivroRequest(
    val titulo: String,
    val sinopse: String,
    val totalPaginas: Int,
    val autor: String,
    val idSituacao: Int,
    val userId: String
)

@Serializable
data class PathLivroRequest(
    val paginasLidas: Int
)
