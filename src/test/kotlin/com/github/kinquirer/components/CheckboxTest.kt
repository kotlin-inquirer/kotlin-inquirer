package com.github.kinquirer.components

import com.github.kinquirer.core.Choice
import com.github.kinquirer.core.KInquirerEvent.*
import com.github.kinquirer.core.toAnsi
import com.github.kinquirer.core.toAnsiStr
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CheckboxTest {

    private val checkbox = CheckboxComponent(
        message = "hello?",
        hint = "please select something",
        choices = listOf(
            Choice("A", "1"),
            Choice("B", "2"),
            Choice("C", "3"),
            Choice("D", "4"),
        ),
        maxNumOfSelection = 2,
        minNumOfSelection = 1,
        pageSize = 2,
    )

    @Test
    fun `test checkbox scrolling down 2 steps`() {
        checkbox.onEventSequence {
            add(KeyPressDown)
            add(KeyPressDown)
        }
        val expected = buildString {
            append("?".toAnsi { bold(); fgGreen() })
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine("   ◯ B")
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "◯ C")
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
        }
        assertEquals(expected, checkbox.render())
    }

    @Test
    fun `test checkbox scrolling down till the end`() {
        checkbox.onEventSequence {
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressDown)
        }
        val expected = buildString {
            append("?".toAnsi { bold(); fgGreen() })
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine("   ◯ C")
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "◯ D")
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
        }
        assertEquals(expected, checkbox.render())
    }

    @Test
    fun `test checkbox scrolling down till the end and back up`() {
        checkbox.onEventSequence {
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressDown)
            add(KeyPressUp)
            add(KeyPressUp)
            add(KeyPressUp)
            add(KeyPressUp)
            add(KeyPressUp)
        }
        val expected = buildString {
            append("?".toAnsi { bold(); fgGreen() })
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "◯ A")
            appendLine("   ◯ B")
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
        }
        assertEquals(expected, checkbox.render())
    }

    @Test
    fun `test checkbox selection max limitation`() {
        checkbox.onEventSequence {
            add(KeyPressSpace)
            add(KeyPressDown)
            add(KeyPressSpace)
            add(KeyPressDown)
            add(KeyPressSpace)
        }
        val expected = buildString {
            append("?".toAnsi { bold(); fgGreen() })
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine("   " + "◉ ".toAnsiStr { fgGreen() } + "B".toAnsiStr { fgCyan(); bold() })
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "◯ C")
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
            appendLine("max selection: 2".toAnsi { bold(); fgRed() })
        }
        assertEquals(expected, checkbox.render())
    }

    @Test
    fun `test checkbox selection min limitation`() {
        checkbox.onEventSequence {
            add(KeyPressEnter)
        }
        val expected = buildString {
            append("?".toAnsi { bold(); fgGreen() })
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "◯ A")
            appendLine("   ◯ B")
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
            appendLine("min selection: 1".toAnsi { bold(); fgRed() })
        }
        assertEquals(expected, checkbox.render())
    }

    @Test
    fun `test checkbox value`() {
        checkbox.onEventSequence {
            add(KeyPressSpace)
            add(KeyPressDown)
            add(KeyPressSpace)
            add(KeyPressEnter)
        }
        assertEquals(listOf("1", "2"), checkbox.value())
    }

    @Test
    fun `test checkbox view options`() {
        val checkbox = CheckboxComponent(
            message = "hello?",
            hint = "please select something",
            choices = listOf(
                Choice("A", "1"),
                Choice("B", "2"),
            ),
            viewOptions = CheckboxViewOptions(
                questionMarkPrefix = "❓",
                cursor = "👉",
                nonCursor = " ",
                checked = " ✅ ",
                unchecked = " ⏹ ",
            )
        )

        checkbox.onEventSequence {
            add(KeyPressSpace)
            add(KeyPressDown)
        }

        val expected = buildString {
            append("❓")
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine("  ✅ " + "A".toAnsiStr { fgCyan(); bold() })
            appendLine("👉 ⏹ B")
        }

        assertEquals(expected, checkbox.render())

    }

}
