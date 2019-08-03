package com.yg.kotlin.inquirer.components

import com.yg.kotlin.inquirer.core.*
import kotlin.math.max
import kotlin.math.min

private class ListComponent(val message: String,
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
            is Event.PressSpace -> {
                selectedIndexes = if (selectedIndexes.contains(courserIndex)) {
                    selectedIndexes.filter { it != courserIndex }.toSet()
                } else {
                    selectedIndexes + courserIndex
                }
            }
            is Event.PressEnter -> interacting = false
        }
    }

    override fun render(): String {
        val menuChoices = choices.mapIndexed { index, s ->
            val d = if (courserIndex == index) {
                " > $s".style(color = Color.Blue, decoration = Decoration.Bold)
            } else {
                "   $s"
            }

            if (selectedIndexes.contains(index)) {
                d.style(color = Color.Blue)
            } else {
                d
            }

        }
        return if (interacting) {
            "? $message\n" + menuChoices.joinToString("\n")
        } else {
            "? $message ${selectedIndexes.joinToString { choices[it] }.style(color = Color.Blue)}"
        }

    }
}

fun KInquirer.promptList(message: String, choices: List<String>): List<String> {
    return prompt(ListComponent(message, choices))
}