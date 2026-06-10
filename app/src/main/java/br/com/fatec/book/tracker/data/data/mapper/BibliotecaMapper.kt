package br.com.fatec.book.tracker.data.data.mapper

import br.com.fatec.book.tracker.data.remote.dto.LivroDto
import br.com.fatec.book.tracker.domain.model.livro.Livro

fun LivroDto.toDomain(): Livro {
    return Livro(
        id = id,
        titulo = titulo,
        sinopse = sinopse,
        totalPaginas = totalPaginas,
        autor = autor,
        idSituacao = idSituacao,
        paginasLidas = paginasLidas,
    )
}
