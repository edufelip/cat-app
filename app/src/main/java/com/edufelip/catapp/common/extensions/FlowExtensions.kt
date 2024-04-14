package com.edufelip.catapp.common.extensions

import com.edufelip.catapp.ui.utils.StateUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform

suspend inline fun <reified T> Flow<T>.collectAsStateUI(collector: FlowCollector<StateUI<T>> = NopCollector) {
    asStateUIFlow().collect(collector = collector)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Flow<T>.asStateUIFlow(): Flow<StateUI<T>> {
    return if (T::class == StateUI::class) {
        this as Flow<StateUI<T>>
    } else {
        transform { value ->
            emit(StateUI.Success(value))
        }
    }
        .onStart { emit(StateUI.Loading) }
        .catch { throwable ->
            emit(StateUI.Error(cause = throwable))
        }
}

object NopCollector : FlowCollector<Any?> {
    override suspend fun emit(value: Any?) {
    }
}