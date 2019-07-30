package com.yg.kotlin.inquirer.core

import com.yg.kotlin.inquirer.core.component.ConfirmationComponent
import com.yg.kotlin.inquirer.core.component.IComponent
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.NonBlockingReader

fun main() {
    interact(ConfirmationComponent("Are You OK ?"))
}

fun runTerminal(func: (reader: NonBlockingReader) -> Unit) {
    val terminal: Terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()
    val reader: NonBlockingReader = terminal.reader()

    func(reader)

    reader.close()
    terminal.close()
}

fun <T> interact(component: IComponent<T>) {
    runTerminal { reader ->
        println(component.render())
        while (true) {
            val event = waitForInteraction(reader)
            if (event !is Event.NotSupportedChar) {
                component.onEvent(event)
                println(component.render())
            }
        }
    }
}

private fun waitForInteraction(reader: NonBlockingReader): Event {
    return when (val c = reader.read()) {
        13 -> Event.PressEnter
        32 -> Event.PressSpace
        27 -> readEscValues(reader)
        else -> Event.Character(c.toChar())
    }
}

fun readEscValues(reader: NonBlockingReader): Event {
    if (reader.read() == '['.toInt()) {
        return when (reader.read()) {
            65 -> Event.PressUp     //"↑"
            66 -> Event.PressDown   //"↓"
            67 -> Event.PressRight  //"→"
            68 -> Event.PressLeft   //"←"
            else -> Event.NotSupportedChar
        }
    }
    return Event.NotSupportedChar
}
