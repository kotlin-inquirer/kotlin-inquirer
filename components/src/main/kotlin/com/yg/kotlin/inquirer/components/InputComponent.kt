package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.*
import java.math.BigDecimal

private class InputComponent(val message: String,
                             default: String,
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

    override fun render(): String {
        val courser = if (interacting) "_".style(decoration = Decoration.Bold) else ""
        val prefix = "?".style(color = Color.Green, decoration = Decoration.Bold)
        val boldMessage = message.style(decoration = Decoration.Bold)
        val transformedValue = transform(value)

        return "$prefix $boldMessage $transformedValue$courser"
    }


}

fun KInquirer.promptInput(
        message: String,
        default: String = "",
        validation: (s: String) -> Boolean = { true },
        filter: (s: String) -> Boolean = { true },
        transform: (s: String) -> String = { it }): String {

    return KInquirer.prompt(InputComponent(message, default, validation, filter, transform))
}

fun KInquirer.promptInputPassword(
        message: String,
        default: String = "",
        mask: String = "*"): String {

    val validation: (s: String) -> Boolean = { true }
    val filter: (s: String) -> Boolean = { true }
    val transform: (s: String) -> String = { it.map { mask }.joinToString("") }

    return KInquirer.prompt(InputComponent(message, default, validation, filter, transform))
}

fun KInquirer.promptInputNumber(
        message: String,
        default: String = "",
        validation: (s: String) -> Boolean = { it.matches("\\d+.?\\d*".toRegex()) },
        filter: (s: String) -> Boolean = { it.matches("\\d*\\.?\\d*".toRegex()) },
        transform: (s: String) -> String = { it }): BigDecimal {

    return BigDecimal(KInquirer.prompt(InputComponent(message, default, validation, filter, transform)))
}
