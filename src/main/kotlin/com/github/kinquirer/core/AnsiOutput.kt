package com.github.kinquirer.core

import com.sun.jna.platform.win32.Kernel32
import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.ansi
import org.fusesource.jansi.AnsiConsole
import java.nio.charset.Charset


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
            val consoleCP = Kernel32.INSTANCE.GetConsoleCP()
            write(
                rendered.toByteArray(
                    // Cp65001 is equivalent to UTF-8
                    if (isOldTerminal && consoleCP != 65001) Charset.forName("Cp$consoleCP")
                    else Charsets.UTF_8
                )
            )
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

