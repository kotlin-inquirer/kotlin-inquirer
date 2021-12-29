package io.github.kinquirer.core

data class Choice<T>(
    val displayName: String,
    val data: T
)

sealed class KInquirerEvent {
    object KeyPressUp : KInquirerEvent()            // MacOS = 27,91,65 | Windows = 27,79,65
    object KeyPressDown : KInquirerEvent()          // MacOS = 27,91,66 | Windows = 27,79,66
    object KeyPressRight : KInquirerEvent()         // MacOS = 27,91,67 | Windows = 27,79,67
    object KeyPressLeft : KInquirerEvent()          // MacOS = 27,91,68 | Windows = 27,79,68
    object KeyPressEnter : KInquirerEvent()         // MacOS = 13       | Windows = 13
    object KeyPressSpace : KInquirerEvent()         // MacOS = 32       | Windows = 32
    object ClearScreen : KInquirerEvent()           // MacOS = 12       | Windows = ?
    object KeyPressBackspace : KInquirerEvent()     // MacOS = 127      | Windows = 8
    object NotSupportedChar : KInquirerEvent()
    data class Character(val c: Char) : KInquirerEvent()
}
