@file:DependsOn("org.jline:jline:3.12.1")

import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.NonBlockingReader


data class State(val inProgress: Boolean, val component: IComponent<*>)

sealed class Event {
    object PressUp : Event()
    object PressDown : Event()
    object PressRight : Event()
    object PressLeft : Event()
    object PressEnter : Event()
    object PressSpace : Event()
    data class Character(val c: Char) : Event()
}

interface IComponent<T> {
    fun onEvent(event: Event) {}
    fun render(): String
    fun value(): T

}

class ConfirmationComponent(private val question: String,
                            private var default: Boolean = false) : IComponent<Boolean> {

    override fun value(): Boolean {
        return default
    }

    override fun onEvent(event: Event) {
        when (event) {
            is Event.PressRight -> default = true
            is Event.PressLeft -> default = false
            is Event.Character -> "${event.c}"
            else -> ""
        }
    }

    override fun render(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun updateState(init: State, eventHandlerFunc: (state: State, event: Event) -> State): State {
    var currentState = init
    while (currentState.inProgress) {
        updateView(currentState)
        val event = waitForEvent()
        currentState = eventHandlerFunc(currentState, event)
    }
    return currentState
}

fun updateView(state: State) {

}


fun waitForEvent(): Event {
    TODO()
}

val initState = State(inProgress = true, component = ConfirmationComponent("Are you sure?"))

val finalState = updateState(initState) { state: State, event: Event ->
    val d = when (event) {
        is Event.PressUp -> ""
        is Event.PressDown -> ""
        is Event.PressRight -> ""
        is Event.PressLeft -> ""
        is Event.PressEnter -> ""
        is Event.PressSpace -> ""
        is Event.Character -> "${event.c}"
        else -> ""
    }

    state
}


fun interact(initIndex: Int = 0, size: Int = 0, func: (index: Int, selectedIndex: Int) -> Unit): Int {
    var currentIndex = initIndex
    var selectedIndex = -1
    runTerminal { reader ->
        while (true) {
            when (val c1 = reader.read()) {
                13 -> {
                    func(currentIndex, currentIndex)
                    return@runTerminal
                }
                ' '.toInt() -> {
                    selectedIndex = currentIndex
                    func(currentIndex, currentIndex)
                }
                '\u001b'.toInt() -> {
                    if (reader.read().toChar() == '[') {
                        when (val cc = reader.read()) {
                            65 -> {
                                currentIndex = if (currentIndex == 0) size - 1 else currentIndex - 1
                                func(currentIndex, selectedIndex)
                            } //"↑"
                            66 -> {
                                currentIndex = currentIndex.plus(1).rem(size)
                                func(currentIndex, selectedIndex)
                            } //"↓"
                            else -> func(currentIndex, selectedIndex)
//                                67 -> "→"
//                                68 -> "←"
//                                else -> cc.toString()
                        }
//                            func(currentIndex)
                    }
                }
                else -> func(currentIndex, selectedIndex).also { println(c1) }
            }
        }
    }
    return currentIndex
}


fun runTerminal(func: (reader: NonBlockingReader) -> Unit) {
//        println("connection to terminal...")
    val terminal: Terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()
    val reader: NonBlockingReader = terminal.reader()
//        println("listening...")

    func(reader)

    reader.close()
    terminal.close()
//        println("terminal closed")
}
