package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.*

class ConfirmComponent(private val message: String,
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
        val yesNo = if (interacting) {
            when {
                confirmed -> "[Yes] No "
                else -> " Yes [No]"
            }
        } else {
            if (confirmed) {
                "Yes"
            } else {
                "No"
            }.style(color = Color.White)
        }

        val questionMark = "?".style(color = Color.Yellow, decoration = Decoration.Bold)
        return "$questionMark " +
                "${message.style(decoration = Decoration.Bold)} " +
                yesNo.style(color = Color.Cyan, decoration = Decoration.Bold)
    }
}
