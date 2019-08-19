package com.yg.kotlin.inquirer.core

private const val ESC = "\u001b["

enum class Color(val colorCode: String) {
    White("${ESC}30m"),
    Red("${ESC}31m"),
    Green("${ESC}32m"),
    Yellow("${ESC}33m"),
    Blue("${ESC}34m"),
    Magenta("${ESC}35m"),
    Cyan("${ESC}36m"),
    Black("${ESC}37m"),
    Reset("${ESC}0m"),
    None("")
}

enum class Decoration(val decorationCode: String) {
    Bold("${ESC}1m"),
    Underline("${ESC}4m"),
    Reversed("${ESC}7m"),
    None("")
}


// ANSI escape code
// https://en.wikipedia.org/wiki/ANSI_escape_code
enum class CursorDirection(private val code: String) {
    Up("A"),
    Down("B"),
    Right("C"),
    Left("D");

    fun steps(n: Int = 1): String = "${ESC}$n$code"
}


//Clear Screen: ${ESC}{n}J clears the screen
//n=0 clears from cursor until end of screen,
//n=1 clears from cursor to beginning of screen
//n=2 clears entire screen
//Clear Line: ${ESC}{n}K clears the current line
//n=0 clears from cursor to end of line
//n=1 clears from cursor to start of line
//n=2 clears entire line

fun String.style(color: Color = Color.None, decoration: Decoration = Decoration.None): String {
    return "${decoration.decorationCode}${color.colorCode}$this${color.colorCode}${decoration.decorationCode}${Color.Reset.colorCode}"
}

fun String.moveCursor(direction: CursorDirection, steps: Int = 1): String {
    return this + direction.steps(steps)
}

fun String.moveCursorUp(steps: Int = 1): String = "$this$ESC$steps" + "A"

fun String.moveCursorDown(steps: Int = 1): String = "$this$ESC$steps" + "B"

fun String.moveCursorRight(steps: Int = 1): String = "$this$ESC$steps" + "C"

fun String.moveCursorLeft(steps: Int = 1): String = "$this$ESC$steps" + "D"

fun String.moveCursorStartOfLine(): String = "$this\r"

fun String.clearScreen(): String = "\r\u001B[0J$this"


fun main() {
//    println("Hello")
//    println("Hello".style(Color.Cyan, Decoration.Bold))
//    println("Hello".style(Color.Cyan, Decoration.Reversed))
//    println("Hello".style(Color.Cyan, Decoration.Underline))
//    println("Hello".style(decoration = Decoration.Bold))
    println("Hello" + CursorDirection.Left.steps(2))
    print("Hello".moveCursor(direction = CursorDirection.Left, steps = 2))
}
