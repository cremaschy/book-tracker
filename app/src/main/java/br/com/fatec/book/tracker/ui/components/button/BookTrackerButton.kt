package br.com.fatec.book.tracker.ui.components.button

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BookTrackerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Definimos o tamanho fixo aqui ou via modifier externo
    Box(
        modifier = modifier
            .size(width = 120.dp, height = 40.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 1.dp.toPx() // Um pouco mais grosso para parecer desenho
            val cornerRadius = 4.dp.toPx()
            val shadowOffset = 2.dp.toPx()

            // 1. Sombra (Deslocada)
            drawRoundRect(
                color = Color.Black.copy(alpha = 0.2f),
                topLeft = Offset(shadowOffset, shadowOffset),
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )

            // 2. Fundo Branco
            drawRoundRect(
                color = Color.White,
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )

            // 3. Borda (Contorno)
            drawRoundRect(
                color = Color.Black,
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                style = Stroke(width = strokeWidth)
            )
        }

        // Texto do Botão
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun BookTrackerButtonPreview() {
    MaterialTheme {
        BookTrackerButton(
            text = "Acessar",
            onClick = {}
        )
    }
}