package br.com.fatec.book.tracker.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val nome: String,
    val ofensiva: Int
)
