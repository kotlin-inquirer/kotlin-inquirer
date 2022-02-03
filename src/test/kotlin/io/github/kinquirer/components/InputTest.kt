package io.github.kinquirer.components

import io.github.kinquirer.core.KInquirerEvent.*
import io.github.kinquirer.core.toAnsi
import org.fusesource.jansi.Ansi.ansi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class InputTest {

    @Test
    fun `test input prompt with hint it should show message with hint`() {
        val message = "hello"
        val hint = "enter your name"
        val inputPrompt = InputComponent(
            message = message,
            hint = hint,
        )
        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append(message.toAnsi { bold() })
            append("   ")
            append(hint.toAnsi { fgBrightBlack() })
            append(ansi().cursorLeft(hint.length + 2))
        }

        Assertions.assertEquals(expected, inputPrompt.render())
        Assertions.assertEquals("", inputPrompt.value())
    }

    @Test
    fun `test input prompt with text input it should show message with input value and new line`() {
        val message = "hello"
        val userInput = "test input"

        val inputPrompt = InputComponent(message = message)
        inputPrompt.onEventSequence {
            userInput.forEach { c -> add(Character(c)) }
            add(KeyPressEnter)
        }

        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append(message.toAnsi { bold() })
            append(" ")
            append(userInput.toAnsi { fgCyan(); bold(); })
            appendLine()
        }

        Assertions.assertEquals(expected, inputPrompt.render())
        Assertions.assertEquals(userInput, inputPrompt.value())
    }

    @Test
    fun `test input prompt it should show message with input user text and in interacting mode`() {
        val inputPrompt = InputComponent(
            message = "prompt message",
            default = "default text",
            hint = "hint text",
            validation = { s -> s.length == 5 },        // accept input with the length of 5
            filter = { s -> !s.contains("l") }    // filter 'l' character
        )

        val userInput = "hello world"
        inputPrompt.onEventSequence {
            userInput.forEach { c -> add(Character(c)) }
            add(KeyPressEnter)
        }

        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append("prompt message".toAnsi { bold() })
            append(" ")
            append("heo word".toAnsi { fgCyan(); bold(); })
        }

        Assertions.assertEquals(expected, inputPrompt.render())
        Assertions.assertEquals("heo word", inputPrompt.value())
        Assertions.assertTrue(inputPrompt.isInteracting())
    }

    @Test
    fun `test input prompt it should show message with input user text, new line and in non-interacting mode`() {
        val inputPrompt = InputComponent(
            message = "prompt message",
            default = "default text",
            hint = "hint text",
            validation = { s -> s.length == 5 },        // accept input with the length of 5
            filter = { s -> !s.contains("l") }    // filter 'l' character
        )

        val userInput = "hello w"
        inputPrompt.onEventSequence {
            userInput.forEach { c -> add(Character(c)) }
            add(KeyPressEnter)
        }

        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append("prompt message".toAnsi { bold() })
            append(" ")
            append("heo w".toAnsi { fgCyan(); bold(); })
            appendLine()
        }

        Assertions.assertEquals(expected, inputPrompt.render())
        Assertions.assertEquals("heo w", inputPrompt.value())
        Assertions.assertFalse(inputPrompt.isInteracting())
    }

    @Test
    fun `test input prompt it should handle non-characters properly`() {
        val inputPrompt = InputComponent(
            message = "prompt message",
            default = "default text",
            hint = "hint text",
        )

        inputPrompt.onEventSequence {
            "hello world".forEach { c -> add(Character(c)) }
            repeat(6) {
                add(KeyPressBackspace)
            }
            add(KeyPressSpace)
            "galaxy".forEach { c -> add(Character(c)) }
            add(KeyPressEnter)
        }

        val expected = buildString {
            append("?".toAnsi { fgGreen(); bold() })
            append(" ")
            append("prompt message".toAnsi { bold() })
            append(" ")
            append("hello galaxy".toAnsi { fgCyan(); bold(); })
            appendLine()
        }

        Assertions.assertEquals(expected, inputPrompt.render())
        Assertions.assertEquals("hello galaxy", inputPrompt.value())
        Assertions.assertFalse(inputPrompt.isInteracting())
    }
}
