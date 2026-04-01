package br.com.fatec.book.tracker.core.view.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun <T> Flow<T>.onEachWithLifecycle(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    onEach: suspend (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle
    LaunchedEffect(key1 = this, key2 = lifecycle) {
        this@onEachWithLifecycle
            .flowWithLifecycle(lifecycle, state)
            .onEach { onEach(it) }
            .launchIn(lifecycleOwner.lifecycle.coroutineScope)
    }
}
