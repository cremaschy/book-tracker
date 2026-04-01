package br.com.fatec.book.tracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import br.com.fatec.book.tracker.core.navigation.Routes
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.PlaceholderScreen
import br.com.fatec.book.tracker.presentation.feature.placeholder.list.state.PlaceholderViewEvent
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavExample()
                    }
                }
            }
        }
    }

    @Composable
    fun NavExample() {
        val backStack = remember { mutableStateListOf<Any>(Routes.PlaceholderList) }

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is Routes.PlaceholderList -> NavEntry(key) {
                        PlaceholderScreen(
                            onEvent = { event ->
                                when (event) {
                                    is PlaceholderViewEvent.NavigateToComments -> {
                                        backStack.add(Routes.CommentList(postId = event.postId))
                                    }
                                }
                            }
                        )
                    }

                    is Routes.CommentList -> NavEntry(key) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text("Comments for Post ID: ${key.postId}")
                        }
                    }

                    else -> NavEntry(Unit) { Text("Unknown route") }
                }
            }
        )
    }
}
