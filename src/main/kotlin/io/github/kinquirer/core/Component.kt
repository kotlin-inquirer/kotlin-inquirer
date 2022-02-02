package io.github.kinquirer.core

public interface Component<T> {
    public fun value(): T
    public fun isInteracting(): Boolean
    public fun onEvent(event: KInquirerEvent)
    public fun render(): String
}
