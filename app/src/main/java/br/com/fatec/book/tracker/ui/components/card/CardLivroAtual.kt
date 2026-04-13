package br.com.fatec.book.tracker.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.fatec.book.tracker.R

@Composable
fun CardLivroAtual(
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                )

                Text(
                    text = "Continue de parou...",
                )
            }
        }
        Text(
            text = "Livro Atual",
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
fun CardLivroAtualPreview() {
    MaterialTheme {
        CardLivroAtual()
    }
}
