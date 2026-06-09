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
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

@Composable
fun DetalheLayout(
    modifier: Modifier = Modifier,
    nomeLivro: String = "Nome do livro",
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        HeaderDetalhe(nomeLivro = nomeLivro)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoBox(
                text = stringResource(id = R.string.detalhe_tipo_livro),
                modifier = Modifier.weight(1f)
            )
            InfoBox(
                text = stringResource(id = R.string.detalhe_tipo_pagina),
                modifier = Modifier.weight(1f)
            )
            InfoBox(
                text = stringResource(id = R.string.detalhe_total_paginas),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        ActionItem(
            icon = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Black)
                )
            },
            text = stringResource(id = R.string.detalhe_selecionar_colecoes)
        )

        Spacer(modifier = Modifier.height(24.dp))

        ActionItem(
            icon = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Black)
                )
            },
            text = stringResource(id = R.string.detalhe_adicionar_etiqueta)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoBox(
                text = stringResource(id = R.string.detalhe_retomar_leitura),
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .border(BorderStroke(1.dp, Color.Black))
                    .background(Color(0xFFD9D9D9))
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.detalhe_desde),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = R.string.detalhe_pausado),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFFFCC00),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
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

            Icon(
                imageVector = BookTrackerDrawableResources.Vectors.buscar,
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .border(2.dp, Color(0xFF333333))
                )
            }
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

@Composable
private fun InfoBox(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color(0xFFD9D9D9))
            .padding(8.dp)
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun ActionItem(
    icon: @Composable () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetalheLayoutPreview() {
    BookTrackerTheme {
        DetalheLayout()
    }
}
