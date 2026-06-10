package br.com.fatec.book.tracker.presentation.feature.detalhe

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheIntent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewState
import br.com.fatec.book.tracker.ui.components.button.BookTrackerButton
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheLayout(
    modifier: Modifier = Modifier,
    state: DetalheViewState,
    onIntent: (DetalheIntent) -> Unit,
) {
    val porcentagem = remember(
        state.livro.paginasLidas,
        state.livro.totalPaginas
    ) {
        if (state.livro.totalPaginas > 0) {
            state.livro.paginasLidas.toFloat() / state.livro.totalPaginas
        } else {
            0f
        }
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            }
        ) {
            AtualizarProgressoSheet(
                paginasAtuais = state.livro.paginasLidas,
                totalPaginas = state.livro.totalPaginas,
                onSalvar = { paginas ->
                    onIntent(
                        DetalheIntent.OnSalvarProgresso(
                            paginas
                        )
                    )
                    showBottomSheet = false
                }
            )
        }
    }

    fun Int.toReadingStatus() = ReadingStatus.fromId(this)

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        HeaderDetalhe(
            titulo = state.livro.titulo,
            onBackClick = {
                onIntent(DetalheIntent.OnBackClicked)
            },
            onDeleteClick = {
                onIntent(DetalheIntent.OnDeleteClicked)
            }
        )
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Autor",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = state.livro.autor,
            )

            Text(
                text = "Total de páginas",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = state.livro.totalPaginas.toString(),
            )

            Text(
                text = "Situação",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = state.livro.idSituacao.toReadingStatus().status
            )

            Text(
                text = "Progresso",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

            Text(
                text = "${state.livro.paginasLidas} / ${state.livro.totalPaginas} páginas",
            )

            LinearProgressIndicator(
                progress = { porcentagem },
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF74A9D8),
            )

            Text(
                text = "${(porcentagem * 100).toInt()}%",
            )

            BookTrackerButton(
                text = "Atualizar progresso",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onIntent(DetalheIntent.OnAtualizarProgressoClicked)
                },
            )
        }
    }
}

@Composable
private fun HeaderDetalhe(
    titulo: String,
    onBackClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
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
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = BookTrackerDrawableResources.Vectors.voltar,
                            contentDescription = null,
                        )
                    }

                    IconButton(
                        onClick = onDeleteClick
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = null,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = titulo,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(start = 24.dp)
                .size(
                    width = 80.dp,
                    height = 120.dp
                )
                .align(Alignment.BottomStart)
                .border(
                    width = 1.dp,
                    color = Color.Black
                )
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

@Composable
fun AtualizarProgressoSheet(
    paginasAtuais: Int,
    totalPaginas: Int,
    onSalvar: (Int) -> Unit
) {
    var paginasLidas by remember {
        mutableStateOf(paginasAtuais.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Atualizar progresso",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = paginasLidas,
            onValueChange = {
                paginasLidas = it
            },
            label = {
                Text("Páginas lidas")
            }
        )

        Text(
            text = "Total de páginas: $totalPaginas"
        )

        BookTrackerButton(
            text = "Salvar",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                paginasLidas.toIntOrNull()?.let {
                    onSalvar(it.coerceIn(0, totalPaginas))
                }
            }
        )
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
                    paginasLidas = 128
                )
            ),
            onIntent = {},
        )
    }
}
