package com.yg.kotlin.inquirer.core.components

import com.yg.kotlin.inquirer.core.Event

class ConfirmationComponent(private val question: String,
                            default: Boolean = false) : IComponent<Boolean> {

    private var confirmed = default

    override fun value(): Boolean {
        return confirmed
    }

    override fun onEvent(event: Event) {
        when (event) {
            is Event.PressLeft -> confirmed = true
            is Event.PressRight -> confirmed = false
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

        return "$question $yesNo"
    }
}
