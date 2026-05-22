package br.com.fatec.book.tracker.presentation.feature.livro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.enums.ReadingStatus
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fatec.book.tracker.R
import br.com.fatec.book.tracker.presentation.feature.cadastro.CadastroLayout
import br.com.fatec.book.tracker.ui.components.button.BookTrackerButton
import br.com.fatec.book.tracker.ui.components.divider.TextDivider
import br.com.fatec.book.tracker.ui.components.textfield.BookTrackerTextField
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources


@Composable

fun AdicionarLivroLayout(
    modifier: Modifier = Modifier,
    onCadastrarLivro: (
        titulo: String,
        autor: String,
        totalPaginas: String,
        descricao: String,
        selectStatus: ReadingStatus,
        currentPage: Int,
        showPageDialog: Boolean,
    ) -> Unit = { _, _, _, _, _, _, _ -> },
    onVoltar: () -> Unit = {},
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var totalPaginas by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var selectedStatus by remember { mutableStateOf(ReadingStatus.READING)}

    var currentPage by remember { mutableStateOf(0) }

    var showPageDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 34.dp, vertical = 52.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ){
        Text(
            text = "Adicionar Livro",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 35.sp
            ),
        )

        Text (
            modifier = Modifier.padding(bottom = 35.dp),
            text = "Insira as informações do livro abaixo",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black
            ),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp),
        ){
            BookTrackerTextField(

                text = titulo,
                onValueChange = {
                    titulo = it
                },
                title = "Livro",
                label = "Título do Livro",

            )

            BookTrackerTextField(

                text = autor,
                onValueChange = {
                    autor = it
                },
                title = "Nome do Autor",
                label = "Nome do Autor",
            )

            BookTrackerTextField(

                text = totalPaginas,
                onValueChange = {
                    totalPaginas = it
                },
                title = "Total de Páginas",
                label = "Total de Páginas",
            )

            BookTrackerTextField(

                text = descricao,
                onValueChange = {
                    descricao = it
                },
                title = "Descrição",
                label = "Descrição",
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            BookTrackerButton(
                modifier = Modifier.weight(1f),
                text = "Voltar",
                onClick = onVoltar
            )
            BookTrackerButton(
                modifier = Modifier.weight(1f),
                text = "Adicionar",
                onClick = {
                    onCadastrarLivro(
                       titulo,
                        autor,
                        totalPaginas,
                        descricao,
                        selectedStatus,
                        currentPage,
                        showPageDialog,
                    )
                },
                enabled = titulo.isNotEmpty() && autor.isNotEmpty() && descricao.isNotEmpty(),
                callToAction = true
            )
        }

    }
}

@Preview
@Composable
fun AdicionarLivroLayoutPreview() {
    MaterialTheme {
        AdicionarLivroLayout()
    }
}