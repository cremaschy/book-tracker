package br.com.fatec.book.tracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleIcon(
    iconSize: Int,
    icon: ImageVector,
    borderColor: Color,
    backgroundSize: Int,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = Color.White,
                shape = CircleShape,
            )
            .clickable(onClick = onClick)
            .size(backgroundSize.dp)
            .border(2.dp, borderColor, CircleShape),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier.size(iconSize.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CircleIconPreview() {
    CircleIcon(
        iconSize = 27,
        backgroundSize = 41,
        icon = Icons.Default.Add,
        borderColor = Color.Gray,
    )
}
