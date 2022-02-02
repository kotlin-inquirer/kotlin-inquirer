package io.github.kinquirer.components

import io.github.kinquirer.core.*
import io.github.kinquirer.core.KInquirerEvent.*
import kotlin.math.max
import kotlin.math.min

internal class CheckboxComponent<T>(
    val message: String,
    val hint: String,
    val choices: List<Choice<T>>,
    private val maxNumOfSelection: Int = Int.MAX_VALUE,
    private val minNumOfSelection: Int = 0,
    private val pageSize: Int = Int.MAX_VALUE,
    private val viewOptions: CheckboxViewOptions = CheckboxViewOptions()
) : Component<List<T>> {

    private val selectedIndices = mutableSetOf<Int>()
    private var cursorIndex = 0
    private var interacting = true
    private var value: List<T> = emptyList()
    private var windowPageStartIndex = 0
    private var errorMessage = ""
    private var infoMessage = ""

    init {
        if (pageSize < choices.size) {
            infoMessage = "(move up and down to reveal more choices)"
        }
    }

    override fun value(): List<T> {
        return value
    }

    override fun isInteracting(): Boolean {
        return interacting
    }

    override fun onEvent(event: KInquirerEvent) {
        errorMessage = ""
        when (event) {
            is KeyPressUp -> {
                cursorIndex = max(0, cursorIndex - 1)
                if (cursorIndex < windowPageStartIndex) {
                    windowPageStartIndex = max(0, windowPageStartIndex - 1)
                }
            }
            is KeyPressDown -> {
                cursorIndex = min(choices.size - 1, cursorIndex + 1)
                if (cursorIndex > windowPageStartIndex + pageSize - 1) {
                    windowPageStartIndex = min(choices.size - 1, windowPageStartIndex + 1)
                }
            }
            is KeyPressSpace -> {
                when {
                    cursorIndex in selectedIndices -> {
                        selectedIndices.remove(cursorIndex)
                    }
                    selectedIndices.size + 1 <= maxNumOfSelection -> {
                        selectedIndices.add(cursorIndex)
                    }
                    selectedIndices.size + 1 > maxNumOfSelection -> {
                        errorMessage = "max selection: $maxNumOfSelection"
                    }
                }
            }
            is KeyPressEnter -> {
                if (selectedIndices.size < minNumOfSelection) {
                    errorMessage = "min selection: $minNumOfSelection"
                } else {
                    interacting = false
                    value = choices.filterIndexed { index, _ -> index in selectedIndices }
                        .map { choice -> choice.data }
                }
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
            // Error message
            if (errorMessage.isNotBlank()) {
                appendLine(errorMessage.toAnsi { bold(); fgRed() })
            }
        } else {
            // Results
            appendLine(
                choices.filterIndexed { index, _ -> index in selectedIndices }
                    .joinToString(", ") { choice -> choice.displayName }
                    .toAnsi { fgCyan(); bold() }
            )
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
        if (currentIndex in selectedIndices) {
            append(viewOptions.checked)
        } else {
            append(viewOptions.unchecked)
        }
    }

    private fun StringBuilder.appendChoice(currentIndex: Int, choice: Choice<T>) {
        if (currentIndex in selectedIndices) {
            append(choice.displayName.toAnsi { fgCyan(); bold() })
        } else {
            append(choice.displayName)
        }
    }

}

private val isOldTerminal: Boolean by lazy { System.getProperty("os.name").contains("win", ignoreCase = true) }

public data class CheckboxViewOptions(
    val questionMarkPrefix: String = "?".toAnsiStr { bold(); fgGreen() },
    val cursor: String = (if (isOldTerminal) " > " else " ❯ ").toAnsiStr { fgBrightCyan() },
    val nonCursor: String = if (isOldTerminal) "   " else "   ",
    val checked: String = (if (isOldTerminal) "(*) " else "◉ ").toAnsiStr { fgGreen() },
    val unchecked: String = (if (isOldTerminal) "( ) " else "◯ "),
)

public fun KInquirer.promptCheckbox(
    message: String,
    choices: List<String>,
    hint: String = "",
    maxNumOfSelection: Int = Int.MAX_VALUE,
    minNumOfSelection: Int = 0,
    pageSize: Int = 10,
    viewOptions: CheckboxViewOptions = CheckboxViewOptions()
): List<String> {
    return promptCheckboxObject(
        message = message,
        choices = choices.map { Choice(it, it) },
        hint = hint,
        maxNumOfSelection = maxNumOfSelection,
        minNumOfSelection = minNumOfSelection,
        pageSize = pageSize,
        viewOptions = viewOptions,
    )
}

public fun <T> KInquirer.promptCheckboxObject(
    message: String,
    choices: List<Choice<T>>,
    hint: String = "",
    maxNumOfSelection: Int = Int.MAX_VALUE,
    minNumOfSelection: Int = 0,
    pageSize: Int = 10,
    viewOptions: CheckboxViewOptions = CheckboxViewOptions(),
): List<T> {
    return prompt(
        CheckboxComponent(
            message = message,
            hint = hint,
            maxNumOfSelection = maxNumOfSelection,
            minNumOfSelection = minNumOfSelection,
            choices = choices,
            pageSize = pageSize,
            viewOptions = viewOptions,
        )
    )
}
