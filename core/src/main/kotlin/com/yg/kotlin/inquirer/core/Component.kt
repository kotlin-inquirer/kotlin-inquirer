package com.yg.kotlin.inquirer.core

interface Component<T> {
    fun value(): T
    fun interacting(): Boolean
    fun onEvent(event: Event)
    fun render(previousView: String = ""): String
}