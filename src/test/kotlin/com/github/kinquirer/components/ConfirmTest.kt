package com.github.kinquirer.components

import com.github.kinquirer.core.KInquirerEvent
import com.github.kinquirer.core.toAnsi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ConfirmTest {

    @Test
    fun `test confirm default=false`() {
        val inputPrompt = ConfirmComponent(
            message = "are you sure?",
            default = false,
        )
        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append("are you sure?".toAnsi { bold() })
            append(" ")
            append(" Yes [No]")
        }
        Assertions.assertEquals(expected, inputPrompt.render())
    }

    @Test
    fun `test confirm default=true`() {
        val inputPrompt = ConfirmComponent(
            message = "are you sure?",
            default = true,
        )
        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append("are you sure?".toAnsi { bold() })
            append(" ")
            append("[Yes] No ")
        }
        Assertions.assertEquals(expected, inputPrompt.render())
    }

    @Test
    fun `test confirm when 'Yes' were selected it should return true`() {
        val inputPrompt = ConfirmComponent(
            message = "are you sure?",
            default = false,
        )
        inputPrompt.onEventSequence {
            add(KInquirerEvent.KeyPressLeft)
            add(KInquirerEvent.KeyPressEnter)
        }
        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append("are you sure?".toAnsi { bold() })
            append(" ")
            appendLine("Yes".toAnsi { fgCyan(); bold() })
        }
        Assertions.assertEquals(expected, inputPrompt.render())
        Assertions.assertEquals(true, inputPrompt.value())
    }
}
