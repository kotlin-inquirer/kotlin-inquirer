@file:DependsOn("com.yg.kotlin.inquirer:core:0.01")
@file:DependsOn("com.yg.kotlin.inquirer:components:0.01")

import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.components.promptInputNumber
import com.yg.kotlin.inquirer.components.promptInputPassword
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.components.promptListMulti
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


