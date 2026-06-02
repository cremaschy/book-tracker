package br.com.fatec.book.tracker.domain.model.livro

data class Livro(
    val id: Int,
    val titulo: String,
    val sinopse: String,
    val totalPaginas: Int,
    val autor: String,
    val idSituacao: Int,
)
