package br.com.fatec.book.tracker.presentation.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.presentation.feature.home.state.HomeViewEvent
import br.com.fatec.book.tracker.ui.components.ErrorScreen
import br.com.fatec.book.tracker.ui.components.LoadingScreen
import br.com.fatec.book.tracker.ui.components.ScreenType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeViewEvent) -> Unit = {},
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.screenType) {
        ScreenType.Loading -> {
            LoadingScreen()
        }

        ScreenType.Content -> {
            HomeLayout(
                state = state,
                onIntent = viewModel::onIntent,
            )
        }

        ScreenType.Error -> {
            ErrorScreen()
        }
    }

    viewModel.viewEvent.onEachWithLifecycle(onEach = { event ->
        onEvent(event)
    })
}
