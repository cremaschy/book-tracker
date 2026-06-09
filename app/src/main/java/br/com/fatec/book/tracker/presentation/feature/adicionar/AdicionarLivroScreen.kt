package br.com.fatec.book.tracker.presentation.feature.adicionar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewEvent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.screen.ErrorScreen
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType
import br.com.fatec.book.tracker.ui.components.LoadingScreen
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
                state = state,
                modifier = modifier,
                onIntent = viewModel::onIntent,
            )
        }

        ScreenType.Error -> {
            ErrorScreen()
        }
    }

    viewModel.viewEvent.onEachWithLifecycle(onEach = {
        onEvent(it)
    })
}
