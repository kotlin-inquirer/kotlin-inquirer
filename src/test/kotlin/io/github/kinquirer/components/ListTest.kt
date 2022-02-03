package io.github.kinquirer.components

import io.github.kinquirer.core.Choice
import io.github.kinquirer.core.KInquirerEvent.*
import io.github.kinquirer.core.toAnsi
import io.github.kinquirer.core.toAnsiStr
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ListTest {

    private val list = ListComponent(
        message = "hello?",
        hint = "please select something",
        choices = listOf(
            Choice("A", "1"),
            Choice("B", "2"),
            Choice("C", "3"),
            Choice("D", "4"),
        ),
        pageSize = 2,
    )

    @Test
    fun `test list scrolling down 2 steps`() {
        list.onEventSequence {
            add(KeyPressDown)
            add(KeyPressDown)
        }
        val expected = buildString {
            append("?".toAnsi { bold(); fgGreen() })
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine("   B")
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "C".toAnsiStr { fgCyan(); bold() })
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
        }
        assertEquals(expected, list.render())
    }

    @Test
    fun `test list scrolling down till the end`() {
        list.onEventSequence {
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
            appendLine("   C")
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "D".toAnsiStr { fgCyan(); bold() })
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
        }
        assertEquals(expected, list.render())
    }

    @Test
    fun `test list scrolling down till the end and back up`() {
        list.onEventSequence {
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
            appendLine(" ❯ ".toAnsiStr { fgBrightCyan() } + "A".toAnsiStr { fgCyan(); bold() })
            appendLine("   B")
            appendLine("(move up and down to reveal more choices)".toAnsi { fgBrightBlack() })
        }
        assertEquals(expected, list.render())
    }

    @Test
    fun `test list value`() {
        list.onEventSequence {
            add(KeyPressDown)
            add(KeyPressEnter)
        }
        assertEquals("2", list.value())
    }

    @Test
    fun `test list view options`() {
        val list = ListComponent(
            message = "hello?",
            hint = "please select something",
            choices = listOf(
                Choice("A", "1"),
                Choice("B", "2"),
            ),
            viewOptions = ListViewOptions(
                questionMarkPrefix = "❓",
                cursor = "👉 ",
                nonCursor = "   ",
            )
        )

        list.onEventSequence {
            add(KeyPressSpace)
            add(KeyPressDown)
        }

        val expected = buildString {
            append("❓")
            append(" ")
            append("hello?".toAnsi { bold() })
            append(" ")
            appendLine("please select something".toAnsi { fgBrightBlack() })
            appendLine("   A")
            appendLine("👉 " + "B".toAnsiStr { fgCyan(); bold() })
        }

        assertEquals(expected, list.render())

    }

}
