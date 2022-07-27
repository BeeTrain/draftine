package dev.draftine.arch.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.launchWhenCreated(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenCreated { this@launchWhenCreated.collect() }
}

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted { this@launchWhenStarted.collect() }
}

fun <T> Flow<T>.launchWhenResumed(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenResumed { this@launchWhenResumed.collect() }
}

fun <T> Flow<T>.observeOnCreated(lifecycleScope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    onEach { action(it) }
        .launchWhenCreated(lifecycleScope)
}

fun <T> Flow<T>.observeOnStarted(lifecycleScope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    onEach { action(it) }
        .launchWhenStarted(lifecycleScope)
}

fun <T> Flow<T>.observeOnResumed(lifecycleScope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    onEach { action(it) }
        .launchWhenResumed(lifecycleScope)
}

fun <T> Flow<T>.observeOnCreated(fragment: Fragment, action: suspend (T) -> Unit) {
    onEach { fragment.view?.run { action(it) } }
        .launchWhenCreated(fragment.lifecycleScope)
}

fun <T> Flow<T>.observeOnStarted(fragment: Fragment, action: suspend (T) -> Unit) {
    onEach { fragment.view?.run { action(it) } }
        .launchWhenStarted(fragment.lifecycleScope)
}

fun <T> Flow<T>.observeOnResumed(fragment: Fragment, action: suspend (T) -> Unit) {
    onEach { fragment.view?.run { action(it) } }
        .launchWhenResumed(fragment.lifecycleScope)
}