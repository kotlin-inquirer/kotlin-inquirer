package io.github.kinquirer.core

import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import java.io.Reader

object KInquirer {

    fun <T> prompt(component: Component<T>): T {
        runTerminal { reader ->
            val readerHandler = KInquirerReaderHandler.getInstance()
            AnsiOutput.display(component.render())
            while (component.isInteracting()) {
                val event = readerHandler.handleInteraction(reader)
                component.onEvent(event)
                AnsiOutput.display(component.render())
            }
        }
        return component.value()
    }

    private fun runTerminal(func: (reader: Reader) -> Unit) {
        val terminal: Terminal = TerminalBuilder.builder()
            .jna(true)
            .system(true)
            .build()
        terminal.enterRawMode()
        val reader = terminal.reader()

        func(reader)

        reader.close()
        terminal.close()
    }
}
