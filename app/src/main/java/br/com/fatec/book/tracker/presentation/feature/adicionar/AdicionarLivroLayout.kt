package br.com.fatec.book.tracker.presentation.feature.adicionar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroIntent
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewState
import br.com.fatec.book.tracker.ui.components.BookTrackerSelectorField
import br.com.fatec.book.tracker.ui.components.bottom.sheet.StatusBottomSheet
import br.com.fatec.book.tracker.ui.components.button.BookTrackerButton
import br.com.fatec.book.tracker.ui.components.headers.BookTrackerHeader
import br.com.fatec.book.tracker.ui.components.textfield.BookTrackerTextField
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarLivroLayout(
    modifier: Modifier = Modifier,
    state: AdicionarLivroViewState,
    onIntent: (AdicionarLivroIntent) -> Unit,
) {
    if (state.showStatusBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onIntent(AdicionarLivroIntent.OnDismissStatusBottomSheet)
            }
        ) {
            StatusBottomSheet(
                selected = state.readingStatus,
                onSelected = {
                    onIntent(
                        AdicionarLivroIntent.OnSituacaoChange(it)
                    )
                }
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BookTrackerHeader(
            onClick = {
                onIntent(AdicionarLivroIntent.OnBackClicked)
            },
            isSearchEnabled = false,
        )

        Text(
            text = "Adicionar Livro",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
        )

        Text(
            modifier = Modifier.padding(bottom = 35.dp),
            text = "Insira as informações do livro abaixo",
            style = MaterialTheme.typography.titleSmall,
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp),
        ) {
            BookTrackerTextField(
                text = state.titulo,
                modifier = modifier.fillMaxWidth(),
                onValueChange = {
                    onIntent(AdicionarLivroIntent.OnTituloChange(it))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                title = "Livro",
                label = "Título do Livro",
            )

            BookTrackerTextField(
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                text = state.autor,
                onValueChange = {
                    onIntent(AdicionarLivroIntent.OnAutorChange(it))
                },
                title = "Autor",
                label = "Nome do Autor",
            )

            BookTrackerTextField(
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                text = state.totalPaginas,
                onValueChange = {
                    onIntent(AdicionarLivroIntent.OnTotalPaginasChange(it))
                },
                title = "Total de Páginas",
                label = "Total de Páginas",
            )

            BookTrackerTextField(
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                text = state.sinopse,
                onValueChange = {
                    onIntent(AdicionarLivroIntent.OnSinopseChange(it))
                },
                title = "Descrição",
                label = "Descrição",
            )

            BookTrackerSelectorField(
                title = "Situação",
                value = state.readingStatus.status,
                onClick = {
                    onIntent(AdicionarLivroIntent.OnSituacaoClicked)
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BookTrackerButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Adicionar",
            onClick = {
                onIntent(AdicionarLivroIntent.OnAdicionarClicked)
            },
            enabled = state.isEnabled,
        )
    }
}

@Preview
@Composable
fun AdicionarLivroLayoutPreview() {
    BookTrackerTheme {
        AdicionarLivroLayout(
            state = AdicionarLivroViewState(),
            onIntent = {}
        )
    }
}
