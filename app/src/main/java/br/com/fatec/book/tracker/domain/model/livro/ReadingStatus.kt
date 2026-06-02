package br.com.fatec.book.tracker.domain.model.livro

enum class ReadingStatus(val id: Int, val status: String) {
    WANT_TO_READ(1, "Quero ler"),
    READING(2, "Estou Lendo"),
    PAUSED(3, "Parei de Ler"),
    FINISHED(4, "Terminei de Ler"),
    DROPPED(5, "Desisti de Ler");

    companion object {
        fun fromId(id: Int) =
            entries.firstOrNull { it.id == id } ?: READING
    }
}
