package br.com.fatec.book.tracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LivroDto(
    val id: Int,
    val titulo: String,
    val sinopse: String,
    val totalPaginas: Int,
    val autor: String,
    val idSituacao: Int,
    val paginasLidas: Int
)
