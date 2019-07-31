package com.yg.kotlin.inquirer.core

interface IComponent<T> {
    fun onEvent(event: Event)
    fun render(): String
    fun value(): T
    fun interacting(): Boolean
}