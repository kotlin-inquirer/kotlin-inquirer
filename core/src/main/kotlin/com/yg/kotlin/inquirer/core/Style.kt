package com.yg.kotlin.inquirer.core

enum class Color(val colorCode: String) {
    White("\u001b[30m"),
    Red("\u001b[31m"),
    Green("\u001b[32m"),
    Yellow("\u001b[33m"),
    Blue("\u001b[34m"),
    Magenta("\u001b[35m"),
    Cyan("\u001b[36m"),
    Black("\u001b[37m"),
    Reset("\u001b[0m"),
    None("")
}

enum class Decoration(val decorationCode: String) {
    Bold("\u001b[1m"),
    Underline("\u001b[4m"),
    Reversed("\u001b[7m"),
    None("")
}

fun String.style(color: Color = Color.None, decoration: Decoration = Decoration.None): String {
    return "${decoration.decorationCode}${color.colorCode}$this${color.colorCode}${decoration.decorationCode}${Color.Reset.colorCode}"
}


fun main() {
    println("Hello")
    println("Hello".style(Color.Cyan, Decoration.Bold))
    println("Hello".style(Color.Cyan, Decoration.Reversed))
    println("Hello".style(Color.Cyan, Decoration.Underline))
    println("Hello".style(decoration = Decoration.Bold))
}