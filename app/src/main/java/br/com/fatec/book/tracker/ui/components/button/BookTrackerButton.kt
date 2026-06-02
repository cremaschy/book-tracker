package br.com.fatec.book.tracker.ui.components.button

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    callToAction: Boolean = false
) {
    val alpha = if (enabled) 1f else 0.5f

    Box(
        modifier = modifier
            .heightIn(min = 40.dp)
            .alpha(alpha)
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 1.dp.toPx()
            val cornerRadius = 4.dp.toPx()
            val shadowOffset = 2.dp.toPx()

            val backgroundColor = if (callToAction) {
                if (enabled) Color(0xFF46E6D9) else Color(0xFF135268)
            } else {
                if (enabled) Color.White else Color.DarkGray
            }

            drawRoundRect(
                color = Color.Black.copy(alpha = 0.2f),
                topLeft = Offset(shadowOffset, shadowOffset),
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )

            drawRoundRect(
                color = backgroundColor,
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )

            drawRoundRect(
                color = Color.Black,
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                style = Stroke(width = strokeWidth)
            )
        }

        val foregroundColor = if (callToAction) {
            if (enabled) Color.Black else Color.White
        } else {
            if (enabled) Color.Black else Color.LightGray
        }

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = foregroundColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
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
