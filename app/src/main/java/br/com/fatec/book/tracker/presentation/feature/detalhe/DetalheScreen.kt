package br.com.fatec.book.tracker.presentation.feature.detalhe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.domain.model.livro.Livro
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheIntent
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetalheScreen(
    livro: Livro,
    modifier: Modifier = Modifier,
    onEvent: (DetalheViewEvent) -> Unit,
    viewModel: DetalheViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(livro) {
        viewModel.onIntent(DetalheIntent.Initialize(livro))
    }

    DetalheLayout(
        state = state,
        onIntent = viewModel::onIntent,
        modifier = modifier
    )

    viewModel.viewEvent.onEachWithLifecycle(onEach = {
        onEvent(it)
    })
}
