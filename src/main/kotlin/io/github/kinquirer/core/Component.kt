package io.github.kinquirer.core

interface Component<T> {
    fun value(): T
    fun isInteracting(): Boolean
    fun onEvent(event: KInquirerEvent)
    fun render(): String
}
