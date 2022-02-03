package io.github.kinquirer.core

public data class Choice<T>(
    val displayName: String,
    val data: T
)

public sealed class KInquirerEvent {
    public object KeyPressUp : KInquirerEvent()            // MacOS = 27,91,65 | Windows = 27,79,65
    public object KeyPressDown : KInquirerEvent()          // MacOS = 27,91,66 | Windows = 27,79,66
    public object KeyPressRight : KInquirerEvent()         // MacOS = 27,91,67 | Windows = 27,79,67
    public object KeyPressLeft : KInquirerEvent()          // MacOS = 27,91,68 | Windows = 27,79,68
    public object KeyPressEnter : KInquirerEvent()         // MacOS = 13       | Windows = 13
    public object KeyPressSpace : KInquirerEvent()         // MacOS = 32       | Windows = 32
    public object ClearScreen : KInquirerEvent()           // MacOS = 12       | Windows = ?
    public object KeyPressBackspace : KInquirerEvent()     // MacOS = 127      | Windows = 8
    public object NotSupportedChar : KInquirerEvent()
    public data class Character(val c: Char) : KInquirerEvent()
}
