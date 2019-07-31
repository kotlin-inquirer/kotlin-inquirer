package com.yg.kotlin.inquirer.core.components

import com.yg.kotlin.inquirer.core.Color
import com.yg.kotlin.inquirer.core.Decoration
import com.yg.kotlin.inquirer.core.Event
import com.yg.kotlin.inquirer.core.style

class ConfirmationComponent(private val question: String,
                            default: Boolean = false) : IComponent<Boolean> {
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
            confirmed -> "[Yes] No "
            else -> " Yes [No]"
        }

        val questionMark = "?".style(color = Color.Yellow, decoration = Decoration.Bold)
        return "$questionMark " +
                "${question.style(decoration = Decoration.Bold)} " +
                yesNo.style(color = Color.Cyan, decoration = Decoration.Bold)
    }
}
