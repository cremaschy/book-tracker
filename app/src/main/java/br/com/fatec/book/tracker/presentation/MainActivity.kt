package br.com.fatec.book.tracker.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.fatec.book.tracker.core.navigation.Routes
import br.com.fatec.book.tracker.core.navigation.TopLevelBackStack
import br.com.fatec.book.tracker.presentation.feature.adicionar.AdicionarLivroScreen
import br.com.fatec.book.tracker.presentation.feature.adicionar.state.AdicionarLivroViewEvent
import br.com.fatec.book.tracker.presentation.feature.biblioteca.BibliotecaScreen
import br.com.fatec.book.tracker.presentation.feature.biblioteca.state.BibliotecaViewEvent
import br.com.fatec.book.tracker.presentation.feature.detalhe.DetalheScreen
import br.com.fatec.book.tracker.presentation.feature.detalhe.state.DetalheViewEvent
import br.com.fatec.book.tracker.presentation.feature.home.HomeScreen
import br.com.fatec.book.tracker.presentation.feature.home.state.HomeViewEvent
import br.com.fatec.book.tracker.presentation.feature.login.LoginScreen
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
                                        else -> {}
                                    }
                                }
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
                                        HomeScreen(
                                            modifier = Modifier.padding(innerPadding),
                                            onEvent = { event ->
                                                when (event) {
                                                    HomeViewEvent.NavigateToLogin -> {
                                                        viewModel.onLogout()
                                                        startActivity(
                                                            Intent(
                                                                this@MainActivity,
                                                                MainActivity::class.java,
                                                            )
                                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP),
                                                        )
                                                    }

                                                    is HomeViewEvent.NavigateToAdicionar -> {
                                                        topLevelBackStack.add(
                                                            Routes.AdicionarLivro
                                                        )

                                                    }

                                                    is HomeViewEvent.NavigateToBiblioteca -> {
                                                        topLevelBackStack.add(
                                                            Routes.Biblioteca
                                                        )
                                                    }
                                                }
                                            },
                                        )
                                    }
                                    entry<Routes.AdicionarLivro> {
                                        AdicionarLivroScreen(
                                            modifier = Modifier.padding(innerPadding),
                                            onEvent = { event ->
                                                when (event) {
                                                    AdicionarLivroViewEvent.NavigateToHome -> {
                                                        topLevelBackStack.removeLast()
                                                    }
                                                }
                                            }

                                        )
                                    }
                                    entry<Routes.Biblioteca> {
                                        BibliotecaScreen(
                                            modifier = Modifier.padding(innerPadding),
                                            onEvent = { event ->
                                                when (event) {
                                                    BibliotecaViewEvent.NavigateToHome -> {
                                                        topLevelBackStack.removeLast()
                                                    }

                                                    is BibliotecaViewEvent.NavigateToDetalhe -> {
                                                        topLevelBackStack.add(
                                                            Routes.Detalhes(event.livro)
                                                        )
                                                    }
                                                }
                                            }
                                        )
                                    }

                                    entry<Routes.Detalhes> {
                                        DetalheScreen(
                                            livro = it.livro,
                                            modifier = Modifier.padding(innerPadding),
                                            onEvent = { event ->
                                                when (event) {
                                                    DetalheViewEvent.NavigateToBiblioteca -> {
                                                        topLevelBackStack.removeLast()
                                                    }
                                                }
                                            }
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
