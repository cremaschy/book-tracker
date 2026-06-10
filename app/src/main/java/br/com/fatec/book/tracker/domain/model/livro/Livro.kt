package br.com.fatec.book.tracker.domain.model.livro

import kotlinx.serialization.Serializable

@Serializable
data class Livro(
    val id: Int? = null,
    val titulo: String,
    val sinopse: String,
    val totalPaginas: Int,
    val autor: String,
    val idSituacao: Int,
    val paginasLidas: Int = 0
)
