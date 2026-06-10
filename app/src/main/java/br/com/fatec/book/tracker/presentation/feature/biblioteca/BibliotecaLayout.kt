package br.com.fatec.book.tracker.presentation.feature.biblioteca

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaIntent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewState
import br.com.fatec.book.tracker.ui.components.BookTrackerDropdownFiltro
import br.com.fatec.book.tracker.ui.components.headers.BookTrackerHeader
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

@Composable
fun BibliotecaLayout(
    state: BibliotecaViewState,
    onIntent: (BibliotecaIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BookTrackerHeader(
            onClick = {
                onIntent(BibliotecaIntent.OnBackClicked)
            },
            isSearchEnabled = false,
        )

        Text(
            text = state.filterSelected?.status ?: "Minha Biblioteca",
            style = MaterialTheme.typography.titleLarge,
        )

        BookTrackerDropdownFiltro(
            options = ReadingStatus.entries,
            selected = state.filterSelected,
            onSelected = {
                onIntent(
                    BibliotecaIntent.OnFilterSelected(it)
                )
            },
        )

        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = state.livros,
                key = { it.id ?: 0 },
            ) { livro ->
                LivroItem(
                    livro = livro,
                    onClick = {
                        onIntent(BibliotecaIntent.OnLivroClicked(livro))
                    },
                )
            }
        }
    }
}

@Composable
fun LivroItem(
    livro: Livro,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFD9D9D9),
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = BookTrackerDrawableResources.Images.livro,
                contentDescription = null,
            )

            Text(
                text = livro.titulo,
            )
        }
    }
}

@Preview
@Composable
fun BibliotecaLayoutPreview() {
    BookTrackerTheme {
        BibliotecaLayout(
            state = BibliotecaViewState(
                livros = listOf(
                    Livro(
                        id = 1,
                        titulo = "Dom Casmurro",
                        sinopse = "Um romance sobre ciume e memoria.",
                        totalPaginas = 256,
                        autor = "Machado de Assis",
                        idSituacao = 1,
                    ),
                    Livro(
                        id = 2,
                        titulo = "A Hora da Estrela",
                        sinopse = "Historia de Macabea no Rio.",
                        totalPaginas = 96,
                        autor = "Machado de Assis",
                        idSituacao = 1,
                    ),
                    Livro(
                        id = 3,
                        titulo = "1984",
                        sinopse = "Distopia sobre vigilancia total.",
                        totalPaginas = 328,
                        autor = "Machado de Assis",
                        idSituacao = 3,
                    )
                )
            ),
            onIntent = {},
        )
    }
}
