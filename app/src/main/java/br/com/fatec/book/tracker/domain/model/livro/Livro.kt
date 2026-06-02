package br.com.fatec.book.tracker.domain.model.livro

data class Livro(
    val titulo: String,
    val isbn: String,
    val paginaTotal: Int,
    val idioma: String,
    val autores: List<String>? = null,
    val tradutores: List<String>? = null,
    val ilustradores: List<String>? = null,
    val narradores: List<String>? = null,
    val descriscao: String? = null,
)