package com.yg.kotlin.inquirer.core.components

import com.yg.kotlin.inquirer.core.Event

interface IComponent<T> {
    fun onEvent(event: Event) {}
    fun render(): String
    fun value(): T
}