package com.yg.kotlin.inquirer.core

data class State(val inProgress: Boolean, val component: Component<*>)

sealed class Event {
    object PressUp : Event()
    object PressDown : Event()
    object PressRight : Event()
    object PressLeft : Event()
    object PressEnter : Event()
    object PressSpace : Event()
    object NotSupportedChar : Event()
    data class Character(val c: Char) : Event()
}
