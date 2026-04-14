package br.com.fatec.book.tracker.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String
)
