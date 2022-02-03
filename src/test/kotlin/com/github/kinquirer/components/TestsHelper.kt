package com.github.kinquirer.components

import com.github.kinquirer.core.Component
import com.github.kinquirer.core.KInquirerEvent


internal fun <T> Component<T>.onEventSequence(func: MutableList<KInquirerEvent>.() -> Unit) {
    val events = mutableListOf<KInquirerEvent>()
    events.func()
    events.forEach { event -> onEvent(event) }
}
