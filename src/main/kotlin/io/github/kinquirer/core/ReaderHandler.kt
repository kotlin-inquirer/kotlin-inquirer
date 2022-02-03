package io.github.kinquirer.core

import io.github.kinquirer.core.KInquirerEvent.*
import java.io.Reader

private val isOldTerminal: Boolean = System.getProperty("os.name").contains("win", ignoreCase = true)

internal interface KInquirerReaderHandler {
    companion object {
        fun getInstance(): KInquirerReaderHandler = when {
            isOldTerminal -> KInquirerReaderHandlerOldTerminal
            else -> KInquirerReaderHandlerNewTerminal
        }
    }

    fun handleInteraction(reader: Reader): KInquirerEvent
}

internal object KInquirerReaderHandlerNewTerminal : KInquirerReaderHandler {

    override fun handleInteraction(reader: Reader): KInquirerEvent {
        return when (val c = reader.read()) {
            127 -> KeyPressBackspace
            13 -> KeyPressEnter
            32 -> KeyPressSpace
            12 -> ClearScreen
            27 -> readEscValues(reader)
            else -> Character(Char(c))
        }
    }

    private fun readEscValues(reader: Reader): KInquirerEvent {
        if (reader.read() == 91) {
            return when (reader.read()) {
                65 -> KeyPressUp     //"↑"
                66 -> KeyPressDown   //"↓"
                67 -> KeyPressRight  //"→"
                68 -> KeyPressLeft   //"←"
                else -> NotSupportedChar
            }
        }
        return NotSupportedChar
    }
}

internal object KInquirerReaderHandlerOldTerminal : KInquirerReaderHandler {
    override fun handleInteraction(reader: Reader): KInquirerEvent {
        return when (val c = reader.read()) {
            8 -> KeyPressBackspace
            13 -> KeyPressEnter
            32 -> KeyPressSpace
            12 -> ClearScreen // (TODO)
            27 -> readEscValues(reader)
            else -> Character(Char(c))
        }
    }

    private fun readEscValues(reader: Reader): KInquirerEvent {
        if (reader.read() == 79) {
            return when (reader.read()) {
                65 -> KeyPressUp     //"↑"
                66 -> KeyPressDown   //"↓"
                67 -> KeyPressRight  //"→"
                68 -> KeyPressLeft   //"←"
                else -> NotSupportedChar
            }
        }
        return NotSupportedChar
    }
}
