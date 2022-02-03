package com.github.kinquirer.components

import com.github.kinquirer.core.*
import com.github.kinquirer.core.KInquirerEvent.*
import com.github.kinquirer.core.toAnsi
import com.github.kinquirer.core.toAnsiStr
import kotlin.math.max
import kotlin.math.min

internal class ListComponent<T>(
    private val message: String,
    private val hint: String,
    private val choices: List<Choice<T>>,
    private val pageSize: Int = Int.MAX_VALUE,
    private val viewOptions: ListViewOptions = ListViewOptions()
) : Component<T> {

    private var cursorIndex = 0
    private var interacting = true
    private var windowPageStartIndex = 0
    private var infoMessage = ""

    init {
        if (pageSize < choices.size) {
            infoMessage = "(move up and down to reveal more choices)"
        }
    }

    override fun value(): T {
        return choices[cursorIndex].data
    }

    override fun isInteracting(): Boolean {
        return interacting
    }

    override fun onEvent(event: KInquirerEvent) {
        when (event) {
            KeyPressUp -> {
                cursorIndex = max(0, cursorIndex - 1)
                if (cursorIndex < windowPageStartIndex) {
                    windowPageStartIndex = max(0, windowPageStartIndex - 1)
                }
            }
            KeyPressDown -> {
                cursorIndex = min(choices.size - 1, cursorIndex + 1)
                if (cursorIndex > windowPageStartIndex + pageSize - 1) {
                    windowPageStartIndex = min(choices.size - 1, windowPageStartIndex + 1)
                }
            }
            KeyPressEnter -> {
                interacting = false
            }
            else -> {}
        }
    }

    override fun render(): String = buildString {
        // Question mark character
        append(viewOptions.questionMarkPrefix)
        append(" ")

        // Message
        append(message.toAnsi { bold() })
        append(" ")

        if (interacting) {
            // Hint
            if (hint.isNotBlank()) {
                append(hint.toAnsi { fgBrightBlack() })
            }
            appendLine()
            // Choices
            choices.forEachIndexed { index, choice ->
                appendListRow(index, choice)
            }
            // Info message
            if (infoMessage.isNotBlank()) {
                appendLine(infoMessage.toAnsi { fgBrightBlack() })
            }
        } else {
            // Result
            appendLine(choices[cursorIndex].displayName.toAnsi { fgCyan(); bold() })
        }
    }

    private fun StringBuilder.appendListRow(currentIndex: Int, choice: Choice<T>) {
        if (currentIndex in windowPageStartIndex until windowPageStartIndex + pageSize) {
            appendCursor(currentIndex)
            appendChoice(currentIndex, choice)
            appendLine()
        }
    }

    private fun StringBuilder.appendCursor(currentIndex: Int) {
        if (currentIndex == cursorIndex) {
            append(viewOptions.cursor)
        } else {
            append(viewOptions.nonCursor)
        }
    }

    private fun StringBuilder.appendChoice(currentIndex: Int, choice: Choice<T>) {
        if (currentIndex == cursorIndex) {
            append(choice.displayName.toAnsi { fgCyan(); bold() })
        } else {
            append(choice.displayName)
        }
    }

}

private val isOldTerminal: Boolean by lazy { System.getProperty("os.name").contains("win", ignoreCase = true) }

public data class ListViewOptions(
    val questionMarkPrefix: String = "?".toAnsiStr { bold(); fgGreen() },
    val cursor: String = (if (isOldTerminal) " > " else " ❯ ").toAnsiStr { fgBrightCyan() },
    val nonCursor: String = "   ",
)

public fun KInquirer.promptList(
    message: String,
    choices: List<String> = emptyList(),
    hint: String = "",
    pageSize: Int = Int.MAX_VALUE,
    viewOptions: ListViewOptions = ListViewOptions()
): String {
    return promptListObject(
        message = message,
        hint = hint,
        choices = choices.map { choice -> Choice(choice, choice) },
        pageSize = pageSize,
        viewOptions = viewOptions,
    )
}

public fun <T> KInquirer.promptListObject(
    message: String,
    choices: List<Choice<T>> = emptyList(),
    hint: String = "",
    pageSize: Int = Int.MAX_VALUE,
    viewOptions: ListViewOptions = ListViewOptions()
): T {
    return prompt(
        ListComponent(
            message = message,
            hint = hint,
            choices = choices,
            pageSize = pageSize,
            viewOptions = viewOptions,
        )
    )
}
