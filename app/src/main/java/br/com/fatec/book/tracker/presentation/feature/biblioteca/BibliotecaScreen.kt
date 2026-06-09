package br.com.fatec.book.tracker.presentation.feature.biblioteca

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaIntent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewEvent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.screen.ErrorScreen
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType
import br.com.fatec.book.tracker.ui.components.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BibliotecaScreen(
    modifier: Modifier = Modifier,
    onEvent: (BibliotecaViewEvent) -> Unit,
    viewModel: BibliotecaViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.screenType) {
        ScreenType.Loading -> {
            LoadingScreen()
        }

        ScreenType.Content -> {
            BibliotecaLayout(
                modifier = modifier,
                state = state,
                onIntent = viewModel::onIntent
            )
        }

        ScreenType.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.onIntent(BibliotecaIntent.RetryLoad)
                }
            )
        }
    }

    viewModel.viewEvent.onEachWithLifecycle(onEach = {
        onEvent(it)
    })
}
