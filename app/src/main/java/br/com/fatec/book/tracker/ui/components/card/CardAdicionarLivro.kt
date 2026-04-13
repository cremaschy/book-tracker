package br.com.fatec.book.tracker.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun CardAdicionarLivro(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedCard(
            onClick = onClick,
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(RectangleShape),
            border = BorderStroke(2.dp, Color.Black),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Tem algum livro que deseja adicionar?",
                    style = MaterialTheme.typography.bodySmall
                )

                Icon(
                    contentDescription = null,
                    imageVector = Icons.Default.Add,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(96.dp)
                        .offset(x = 24.dp, y = 24.dp)
                )
            }
        }

        Text(
            text = "Adicionar livro",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 16.dp, y = 0.dp)
                .background(Color.White)
                .padding(horizontal = 8.dp)
                .zIndex(1f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun CardAdicionarLivroPreview() {
    MaterialTheme {
        CardAdicionarLivro()
    }
}
