package br.com.fatec.book.tracker.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.fatec.book.tracker.core.navigation.Routes
import br.com.fatec.book.tracker.core.navigation.TopLevelBackStack
import br.com.fatec.book.tracker.domain.model.Home
import br.com.fatec.book.tracker.presentation.feature.cadastro.CadastroLayout
import br.com.fatec.book.tracker.presentation.feature.home.HomeLayout
import br.com.fatec.book.tracker.presentation.feature.livro.AdicionarLivroLayout
import br.com.fatec.book.tracker.presentation.feature.login.LoginLayout
import br.com.fatec.book.tracker.presentation.feature.login.LoginScreen
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginViewEvent
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.PlaceholderScreen
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewEvent
import br.com.fatec.book.tracker.ui.components.LoadingScreen
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
        )
        setContent {
            BookTrackerTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()

                when (state) {
                    MainState.Loading -> {
                        Scaffold(
                            modifier = Modifier.fillMaxSize()
                        ) { innerPadding ->
                            LoadingScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                            )
                        }
                    }

                    MainState.Login -> {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                        ) { innerPadding ->
                            LoginScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                onEvent = { event ->
                                    when (event) {
                                        LoginViewEvent.NavigateToRegister -> {
                                            viewModel.navigateToRegister()
                                        }

                                        else -> {}
                                    }
                                }
                            )
                        }
                    }

                    MainState.Register -> {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                        ) { innerPadding ->
                            CadastroLayout(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                onRegister = { _, _ -> },
                                onLogin = {},
                            )
                        }
                    }

                    MainState.Logged -> {
                        val topLevelBackStack =
                            remember { TopLevelBackStack<NavKey>(Routes.Home) }
                        Scaffold(
                            modifier = Modifier.fillMaxSize()
                        ) { innerPadding ->
                            NavDisplay(
                                backStack = topLevelBackStack.backStack,
                                onBack = { topLevelBackStack.removeLast() },
                                entryProvider = entryProvider {
                                    entry<Routes.Home> {
                                        HomeLayout(
                                            modifier = Modifier.padding(innerPadding),
                                            home = Home(
                                                nome = "Gustavo",
                                                ofensiva = 3,
                                            ),
                                        )
                                    }
                                },
                            )
                        }
                    }

                    MainState.SessionExpired -> {
                        AlertDialog(
                            onDismissRequest = {},
                            title = { Text(text = "Sessão expirada") },
                            text = { Text(text = "Sua sessão expirou. Faça login novamente para continuar.") },
                            confirmButton = {
                                TextButton(onClick = { viewModel.onSessionExpiredConfirmed() }) {
                                    Text(text = "OK")
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}
