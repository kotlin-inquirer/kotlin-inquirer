package com.yg.kotlin.inquirer.core

data class State(val inProgress: Boolean, val component: Component<*>)
data class Choice<T>(val name: String, val data: T)

sealed class Event {
    object PressUp : Event()
    object PressDown : Event()
    object PressRight : Event()
    object PressLeft : Event()
    object PressEnter : Event()
    object PressSpace : Event()
    object PressClearScreen : Event()
    object PressBackspace : Event()
    object NotSupportedChar : Event()
    data class Character(val c: Char) : Event()
}
