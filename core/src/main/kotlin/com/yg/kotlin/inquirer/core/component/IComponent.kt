package com.yg.kotlin.inquirer.core.component

import com.yg.kotlin.inquirer.core.Event

interface IComponent<T> {
    fun onEvent(event: Event) {}
    fun render(): String
    fun value(): T
}