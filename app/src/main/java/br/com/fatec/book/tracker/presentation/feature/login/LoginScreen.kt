package br.com.fatec.book.tracker.presentation.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.fatec.book.tracker.core.view.model.onEachWithLifecycle
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginIntent
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginViewEvent
import br.com.fatec.book.tracker.ui.components.ErrorScreen
import br.com.fatec.book.tracker.ui.components.LoadingScreen
import br.com.fatec.book.tracker.ui.components.ScreenType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onEvent: (LoginViewEvent) -> Unit = {},
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.screenType) {
        ScreenType.Loading -> {
            LoadingScreen()
        }

        ScreenType.Content -> {
            LoginLayout(
                modifier = modifier,
                state = state,
                onIntent = viewModel::onIntent
            )
        }

        ScreenType.Error -> {
            ErrorScreen(
                onRetry = {
                    viewModel.onIntent(LoginIntent.OnRetryClicked)
                }
            )
        }
    }

    viewModel.viewEvent.onEachWithLifecycle(onEach = {
        onEvent(it)
    })
}
