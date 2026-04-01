package br.com.fatec.book.tracker.presentation.feature.placeholder.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.model.Post
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.preview.PreviewFactory
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderIntent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewState

@Composable
fun PlaceholderLayout(state: PlaceholderViewState, onIntent: (PlaceholderIntent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(state.posts) {
                PlaceholderItem(post = it, onClick = {
                    onIntent(PlaceholderIntent.OnPostClicked(it.id))
                })
            }
        }
    }
}

@Composable
fun PlaceholderItem(post: Post, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                ),
            )

            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.secondary,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun PlaceholderLayoutPreview() {
    MaterialTheme {
        PlaceholderLayout(state = PreviewFactory.placeholderViewState(), onIntent = {})
    }
}