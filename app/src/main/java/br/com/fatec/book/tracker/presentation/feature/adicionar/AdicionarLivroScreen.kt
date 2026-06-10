package br.com.fatec.book.tracker.presentation.feature.adicionar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroIntent
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewEvent
import br.com.fatec.book.tracker.ui.components.ErrorScreen
import br.com.fatec.book.tracker.ui.components.LoadingScreen
import br.com.fatec.book.tracker.ui.components.ScreenType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AdicionarLivroScreen(
    modifier: Modifier = Modifier,
    onEvent: (AdicionarLivroViewEvent) -> Unit,
    viewModel: AdicionarLivroViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.screenType) {
        ScreenType.Loading -> {
            LoadingScreen()
        }

        ScreenType.Content -> {
            AdicionarLivroLayout(
                modifier = modifier,
                state = state,
                onIntent = viewModel::onIntent
            )
        }

        ScreenType.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.onIntent(AdicionarLivroIntent.OnRetryClicked)
                }
            )
        }
    }

    viewModel.viewEvent.onEachWithLifecycle(onEach = {
        onEvent(it)
    })
}
