package br.com.fatec.book.tracker.ui.components.headers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fatec.book.tracker.ui.components.BookTrackerSearchBar
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources

@Composable
fun BookTrackerHeader(
    onClick: () -> Unit = {},
    isSearchEnabled: Boolean = false,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = BookTrackerDrawableResources.Vectors.voltar,
                contentDescription = null,
            )
        }
        if (isSearchEnabled) {
            BookTrackerSearchBar()
        }
    }
}

@Preview
@Composable
fun BookTrackerHeaderPreview() {
    MaterialTheme {
        BookTrackerHeader()
    }
}
