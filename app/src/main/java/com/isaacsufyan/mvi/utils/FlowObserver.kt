package com.isaacsufyan.mvi.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class FlowObserver<T>(
    lifecycleOwner: LifecycleOwner,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit
) {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(
            LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        job = source.lifecycleScope.launch {
                            flow.collect { collector(it) }
                        }
                    }
                    Lifecycle.Event.ON_STOP -> {
                        job?.cancel()
                        job = null
                    }
                    else -> {
                    }
                }
            }
        )
    }
}

@InternalCoroutinesApi
inline fun <reified T> Flow<T>.observeOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    noinline collector: suspend (T) -> Unit
) = FlowObserver(lifecycleOwner, this, collector)

@InternalCoroutinesApi
fun <T> Flow<T>.observeInLifecycle(
    lifecycleOwner: LifecycleOwner
) = FlowObserver(lifecycleOwner, this, {})
