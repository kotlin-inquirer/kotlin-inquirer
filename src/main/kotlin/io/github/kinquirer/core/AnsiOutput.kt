package io.github.kinquirer.core

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.ansi
import org.fusesource.jansi.AnsiConsole
import kotlin.text.Charsets.UTF_8


internal object AnsiOutput {
    private var prevViewHeight = 0

    fun display(view: String) {
        val rendered = buildString {
            appendAnsi {
                cursorToColumn(0)
                eraseLine(Ansi.Erase.ALL)
                if (prevViewHeight > 2) {
                    repeat(prevViewHeight - 1) {
                        eraseLine(Ansi.Erase.ALL)
                        cursorUpLine()
                    }
                    eraseLine(Ansi.Erase.ALL)
                }
            }
            append(view)

            prevViewHeight = view.lines().size
        }

        with(AnsiConsole.out()) {
            write(rendered.toByteArray(UTF_8))
            flush()
        }
    }

    private fun StringBuilder.appendAnsi(func: Ansi.() -> Unit = {}) {
        val ansi = ansi()
        func(ansi)
        append(ansi)
    }
}

internal fun String.toAnsi(func: Ansi.() -> Unit = {}): Ansi {
    val ansi = ansi()
    func(ansi)
    return ansi.a(this).reset()
}

internal fun String.toAnsiStr(func: Ansi.() -> Unit = {}): String {
    return toAnsi(func).toString()
}

