package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.Color
import com.yg.kotlin.inquirer.core.Component
import com.yg.kotlin.inquirer.core.Decoration
import com.yg.kotlin.inquirer.core.Event
import com.yg.kotlin.inquirer.core.KInquirer
import com.yg.kotlin.inquirer.core.clearScreen
import com.yg.kotlin.inquirer.core.style

private class ConfirmComponent(private val message: String,
                               default: Boolean = false) : Component<Boolean> {
    private var confirmed = default
    private var interacting = true

    override fun value(): Boolean {
        return confirmed
    }

    override fun interacting(): Boolean {
        return interacting
    }

    override fun onEvent(event: Event) {
        when (event) {
            is Event.PressLeft -> confirmed = true
            is Event.PressRight -> confirmed = false
            is Event.PressEnter -> interacting = false
            is Event.Character -> {
                when (event.c) {
                    'y', 'Y' -> confirmed = true
                    'n', 'N' -> confirmed = false
                }
            }
        }
    }

    override fun render(): String {
        val yesNo = when {
            interacting && confirmed -> "[Yes] No "
            interacting && !confirmed -> " Yes [No]"
            !interacting && confirmed -> "Yes\n".style(color = Color.Cyan, decoration = Decoration.Bold)
            !interacting && !confirmed -> "No\n".style(color = Color.Cyan, decoration = Decoration.Bold)
            else -> ""
        }
        val questionMark = "?".style(color = Color.Green, decoration = Decoration.Bold)
        val boldMessage = message.style(decoration = Decoration.Bold)

        return "$questionMark $boldMessage $yesNo".clearScreen()
    }
}

fun KInquirer.promptConfirm(
        message: String,
        default: Boolean = false): Boolean {
    return prompt(ConfirmComponent(message, default))
}
