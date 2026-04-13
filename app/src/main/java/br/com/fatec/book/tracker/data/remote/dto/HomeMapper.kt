package br.com.fatec.book.tracker.data.remote.dto

import br.com.fatec.book.tracker.domain.model.Home

fun HomeResponse.toDomain(): Home {
    return Home(
        nome = nome,
        ofensiva = ofensiva
    )
}
