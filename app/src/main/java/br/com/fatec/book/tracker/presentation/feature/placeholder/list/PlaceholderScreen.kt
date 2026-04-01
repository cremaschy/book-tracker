package br.com.fatec.book.tracker.presentation.feature.placeholder.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.screen.ErrorScreen
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderIntent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewEvent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.ScreenType
import br.com.fatec.book.tracker.ui.components.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlaceholderScreen(
    viewModel: PlaceholderListViewModel = koinViewModel(),
    onEvent: (PlaceholderViewEvent) -> Unit = {},
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(PlaceholderIntent.LoadPosts)
    }

    when (state.screenType) {
        ScreenType.Loading -> {
            LoadingScreen()
        }

        ScreenType.Content -> {
            PlaceholderLayout(state = state, onIntent = viewModel::onIntent)
        }

        ScreenType.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.onIntent(PlaceholderIntent.RetryLoadPosts)
                },
            )
        }
    }

    viewModel.viewEvent.onEachWithLifecycle(onEach = {
        onEvent(it)
    })
}
