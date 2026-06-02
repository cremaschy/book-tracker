package br.com.fatec.book.tracker.presentation.feature.biblioteca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.ui.components.headers.BookTrackerHeader

@Composable
fun BibliotecaLayout(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            .imePadding(),
    ) {
        BookTrackerHeader()
    }
}

@Preview
@Composable
fun BibliotecaLayoutPreview() {
    MaterialTheme {
        BibliotecaLayout()
    }
}
