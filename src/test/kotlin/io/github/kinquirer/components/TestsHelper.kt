package io.github.kinquirer.components

import io.github.kinquirer.core.Component
import io.github.kinquirer.core.KInquirerEvent


internal fun <T> Component<T>.onEventSequence(func: MutableList<KInquirerEvent>.() -> Unit) {
    val events = mutableListOf<KInquirerEvent>()
    events.func()
    events.forEach { event -> onEvent(event) }
}
