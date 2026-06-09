package br.com.fatec.book.tracker.presentation.feature.detalhe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.R
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheIntent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewState
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

@Composable
fun DetalheLayout(
    modifier: Modifier = Modifier,
    state: DetalheViewState,
    onIntent: (DetalheIntent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        HeaderDetalhe(nomeLivro = state.livro.titulo)

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun HeaderDetalhe(
    nomeLivro: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF74A9D8))
                .padding(16.dp)
        ) {
            Text(
                text = nomeLivro,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 24.dp)
                .size(width = 80.dp, height = 120.dp)
                .align(Alignment.BottomStart)
                .border(1.dp, Color.Black)
                .background(Color.White)
        ) {
            Image(
                painter = BookTrackerDrawableResources.Images.livro,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalheLayoutPreview() {
    BookTrackerTheme {
        DetalheLayout(
            state = DetalheViewState(
                livro = Livro(
                    id = 1,
                    titulo = "Dom Casmurro",
                    sinopse = "Um romance sobre ciume e memoria.",
                    totalPaginas = 256,
                    autor = "Machado de Assis",
                    idSituacao = 1,
                )
            ),
            onIntent = {},
        )
    }
}
