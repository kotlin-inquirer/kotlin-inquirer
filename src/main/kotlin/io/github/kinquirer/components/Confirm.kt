package io.github.kinquirer.components

import io.github.kinquirer.core.Component
import io.github.kinquirer.core.KInquirer
import io.github.kinquirer.core.KInquirerEvent
import io.github.kinquirer.core.toAnsi

internal class ConfirmComponent(
    private val message: String,
    default: Boolean = false
) : Component<Boolean> {
    private var confirmed = default
    private var interacting = true

    override fun value(): Boolean {
        return confirmed
    }

    override fun isInteracting(): Boolean {
        return interacting
    }

    override fun onEvent(event: KInquirerEvent) {
        when (event) {
            is KInquirerEvent.KeyPressLeft -> confirmed = true
            is KInquirerEvent.KeyPressRight -> confirmed = false
            is KInquirerEvent.KeyPressEnter -> interacting = false
            is KInquirerEvent.Character -> {
                when (event.c) {
                    'y', 'Y' -> confirmed = true
                    'n', 'N' -> confirmed = false
                }
            }
            else -> confirmed = false
        }
    }

    override fun render(): String = buildString {
        append("?".toAnsi { fgGreen(); bold() })
        append(" ")
        append(message.toAnsi { bold() })
        append(" ")
        when {
            interacting && confirmed -> append("[Yes] No ")
            interacting && !confirmed -> append(" Yes [No]")
            !interacting && confirmed -> appendLine("Yes".toAnsi { fgCyan(); bold() })
            else -> appendLine("No".toAnsi { fgCyan(); bold() })
        }
    }
}

fun KInquirer.promptConfirm(
    message: String,
    default: Boolean = false
): Boolean {
    return prompt(ConfirmComponent(message, default))
}
