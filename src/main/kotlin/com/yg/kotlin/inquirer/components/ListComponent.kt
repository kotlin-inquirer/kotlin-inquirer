package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.Color
import com.yg.kotlin.inquirer.core.Component
import com.yg.kotlin.inquirer.core.CursorDirection
import com.yg.kotlin.inquirer.core.Decoration
import com.yg.kotlin.inquirer.core.Event
import com.yg.kotlin.inquirer.core.KInquirer
import com.yg.kotlin.inquirer.core.moveCursor
import com.yg.kotlin.inquirer.core.moveCursorStartOfLine
import com.yg.kotlin.inquirer.core.style
import kotlin.math.max
import kotlin.math.min

private class ListComponent<T>(val message: String,
                               val hint: String,
                               val multiSelection: Boolean,
                               val selectionColor: Color,
                               val choices: List<Pair<String, T>>,
                               val showPerPage: Int) : Component<List<T>> {

    private var selectedIndexes: Set<Int> = emptySet()
    private var courserIndex: Int = 0
    private var interacting = true


    override fun value() = choices.filterIndexed { index, _ -> selectedIndexes.contains(index) }.map { it.second }

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
        val menuChoices = choices.map { it.first }.mapIndexed { index, choice ->
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
        }.drop(max(courserIndex + 1 - showPerPage, 0)).take(showPerPage)


        val questionMark = "?".style(color = Color.Green, decoration = Decoration.Bold)
        val boldMessage = message.style(decoration = Decoration.Bold)

        print("\u001b[0J")
        return if (interacting) {
            "$questionMark $boldMessage  ${hint.style(color = Color.Black)}\n" + menuChoices.joinToString("\n").moveCursorStartOfLine()
                    .moveCursor(CursorDirection.Up, menuChoices.size)
                    .moveCursor(CursorDirection.Right, message.length + 3)
        } else {
            "$questionMark $boldMessage ${selectedIndexes.joinToString { choices[it].first }.style(color = selectionColor, decoration = Decoration.Bold)}\n\u001b[0J"
        }

    }
}

fun KInquirer.promptList(
        message: String,
        choices: List<String>,
        hint: String = "",
        selectionColor: Color = Color.Cyan,
        showPerPage: Int = 10): String {

    return promptListObject(
            message = message,
            choices = choices.map { it to it },
            hint = hint,
            selectionColor = selectionColor,
            showPerPage = showPerPage
    )
}

fun <T> KInquirer.promptListObject(
        message: String,
        choices: List<Pair<String, T>>,
        hint: String = "",
        selectionColor: Color = Color.Cyan,
        showPerPage: Int = 10): T {

    return prompt(ListComponent(
            message = message,
            hint = hint,
            multiSelection = false,
            selectionColor = selectionColor,
            choices = choices,
            showPerPage = showPerPage
    )).first()
}

fun KInquirer.promptListMulti(
        message: String,
        choices: List<String>,
        hint: String = "",
        selectionColor: Color = Color.Cyan,
        showPerPage: Int = 10): List<String> {

    return promptListMultiObject(
            message = message,
            choices = choices.map { it to it },
            hint = hint,
            selectionColor = selectionColor,
            showPerPage = showPerPage
    )
}

fun <T> KInquirer.promptListMultiObject(
        message: String,
        choices: List<Pair<String, T>>,
        hint: String = "",
        selectionColor: Color = Color.Cyan,
        showPerPage: Int = 10): List<T> {

    return prompt(ListComponent(
            message = message,
            hint = hint,
            multiSelection = true,
            selectionColor = selectionColor,
            choices = choices,
            showPerPage = showPerPage
    ))
}