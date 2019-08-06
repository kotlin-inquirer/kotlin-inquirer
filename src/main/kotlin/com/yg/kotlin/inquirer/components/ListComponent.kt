package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.*
import kotlin.math.max
import kotlin.math.min

private class ListComponent(val message: String,
                            val hint: String,
                            val multiSelection: Boolean,
                            val selectionColor: Color,
                            val choices: List<String>) : Component<List<String>> {

    private var selectedIndexes: Set<Int> = emptySet()
    private var courserIndex: Int = 0
    private var interacting = true


    override fun value(): List<String> = choices.filterIndexed { index, _ -> selectedIndexes.contains(index) }

    override fun interacting(): Boolean = interacting

    override fun onEvent(event: Event) {
        when (event) {
            is Event.PressUp -> courserIndex = max(courserIndex - 1, 0)
            is Event.PressDown -> courserIndex = min(courserIndex + 1, choices.size - 1)
            is Event.PressSpace -> handleSelectedIndex()
            is Event.PressEnter -> handleFinalSelected()
        }
    }

    private fun handleSelectedIndex() {
        if (multiSelection) {
            selectedIndexes = if (selectedIndexes.contains(courserIndex)) {
                selectedIndexes.filter { it != courserIndex }.toSet()
            } else {
                selectedIndexes + courserIndex
            }
        }
    }

    private fun handleFinalSelected() {
        interacting = false
        if (!multiSelection) {
            selectedIndexes = selectedIndexes + courserIndex
        }
    }

    override fun render(): String {
        val menuChoices = choices.mapIndexed { index, choice ->
            val isChecked = selectedIndexes.contains(index)
            val isHover = courserIndex == index
            val checkIcon = if (multiSelection) {
                if (isChecked) " ◉ ".style(color = Color.Green) else " ◯ "
            } else {
                ""
            }
            val pointerIcon = if (isHover) " ❯" else "  "
            val boldChoice = if (isHover) choice.style(color = selectionColor, decoration = Decoration.Bold) else choice
            "$pointerIcon$checkIcon $boldChoice"
        }


        val questionMark = "?".style(color = Color.Green, decoration = Decoration.Bold)
        val boldMessage = message.style(decoration = Decoration.Bold)

        return if (interacting) {
            "$questionMark $boldMessage  ${hint.style(color = Color.Black)}\n" + menuChoices.joinToString("\n").moveCursorStartOfLine()
                    .moveCursor(CursorDirection.Up, menuChoices.size)
                    .moveCursor(CursorDirection.Right, message.length + 3)
        } else {
            "$questionMark $boldMessage ${selectedIndexes.joinToString { choices[it] }.style(color = selectionColor, decoration = Decoration.Bold)}\n\u001b[0J"
        }

    }
}

fun KInquirer.promptList(
        message: String,
        choices: List<String>,
        hint: String = "",
        selectionColor: Color = Color.Cyan): String {
    return prompt(ListComponent(message, hint, multiSelection = false, selectionColor = selectionColor, choices = choices)).first()
}

fun KInquirer.promptListMulti(
        message: String,
        choices: List<String>,
        hint: String = "",
        selectionColor: Color = Color.Cyan): List<String> {
    return prompt(ListComponent(message, hint, multiSelection = true, selectionColor = selectionColor, choices = choices))
}