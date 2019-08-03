@file:DependsOn("com.yg.kotlin.inquirer:core:1.0")
@file:DependsOn("com.yg.kotlin.inquirer:components:1.0")

import com.yg.kotlin.inquirer.components.*
import com.yg.kotlin.inquirer.core.KInquirer

val componentName = KInquirer.promptList("What component do you want to test?", listOf(
        "Input",
        "InputNumber",
        "InputPassword",
        "Confirm",
        "List",
        "ListMultiSelection"
))

when (componentName) {
    "Input" -> KInquirer.promptInput("Enter Your Name:", hint = "(Full Name)")
    "InputNumber" -> KInquirer.promptInputNumber("Enter Your Age:")
    "InputPassword" -> KInquirer.promptInputPassword("Enter Your Password:")
    "Confirm" -> KInquirer.promptConfirm("Is this correct?")
    "List" -> KInquirer.promptList("Select Something?", listOf("Hello", "World", "Foo", "Bar"))
    "ListMultiSelection" -> KInquirer.promptListMulti("Pick Some Stuff:", listOf("A", "B", "C", "D"))
}


