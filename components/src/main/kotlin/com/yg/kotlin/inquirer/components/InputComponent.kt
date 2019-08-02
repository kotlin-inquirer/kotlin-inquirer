package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.*
import java.math.BigDecimal

private class InputComponent(val message: String,
                             default: String,
                             val hint: String = "",
                             val validation: (s: String) -> Boolean,
                             val filter: (s: String) -> Boolean,
                             val transform: (s: String) -> String) : Component<String> {

    private var value = default
    private var interacting = true

    override fun value(): String = value

    override fun interacting(): Boolean = interacting

    override fun onEvent(event: Event) {
        when (event) {
            is Event.PressEnter -> {
                if (validation(value)) {
                    interacting = false
                }
            }
            is Event.PressBackspace -> value = value.dropLast(1)
            is Event.PressSpace -> {
                if (filter(" ")) {
                    value += " "
                }
            }
            is Event.Character -> {
                val tempVal = value + event.c
                if (filter(tempVal)) {
                    value = tempVal
                }
            }
        }
    }

    override fun render(previousView: String): String {
        val prefix = "?".style(color = Color.Green, decoration = Decoration.Bold)
        val boldMessage = message.style(decoration = Decoration.Bold)
        val hintView = if (value.isNotBlank() || hint.isBlank()) "" else "  " + hint.style(color = Color.Black).moveCursor(CursorDirection.Left, hint.length + 2)
        val transformedValue = transform(value) + hintView

        if (!interacting) {
            return "$prefix $boldMessage $transformedValue\n"
        }

        return "$prefix $boldMessage $transformedValue"
    }

}

fun KInquirer.promptInput(
        message: String,
        default: String = "",
        hint: String = "",
        validation: (s: String) -> Boolean = { true },
        filter: (s: String) -> Boolean = { true },
        transform: (s: String) -> String = { it }): String {

    return prompt(InputComponent(message, default, hint, validation, filter, transform))
}

fun KInquirer.promptInputPassword(
        message: String,
        default: String = "",
        hint: String = "",
        mask: String = "*"): String {

    val validation: (s: String) -> Boolean = { true }
    val filter: (s: String) -> Boolean = { true }
    val transform: (s: String) -> String = { it.map { mask }.joinToString("") }

    return prompt(InputComponent(message, default, hint, validation, filter, transform))
}

fun KInquirer.promptInputNumber(
        message: String,
        default: String = "",
        hint: String = "",
        validation: (s: String) -> Boolean = { it.matches("\\d+.?\\d*".toRegex()) },
        filter: (s: String) -> Boolean = { it.matches("\\d*\\.?\\d*".toRegex()) },
        transform: (s: String) -> String = { it }): BigDecimal {

    return BigDecimal(prompt(InputComponent(message, default, hint, validation, filter, transform)))
}
