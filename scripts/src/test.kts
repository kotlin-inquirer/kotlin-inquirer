@file:DependsOn("com.yg.kotlin.inquirer:core:0.01")
@file:DependsOn("com.yg.kotlin.inquirer:components:0.01")

import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.core.KInquirer

val fullName = KInquirer.promptInput("Enter Your Name:", hint = "Full Name")
//val age = KInquirer.promptInputNumber("Enter Your Age:", hint = "0-99")
//val password = KInquirer.promptInputPassword("Enter Your Password:", hint = "password")
//val f = KInquirer.promptList("Select Something?", listOf("Hello", "World", "Foo", "Bar"))
//val f = KInquirer.promptListMulti("Pick Some Stuff:", listOf("A", "B", "C", "D"))
val f = KInquirer.promptConfirm("Are you sure?")

//
//val componentName = KInquirer.promptList("What component do you want to test?", listOf(
//        "Input",
//        "InputNumber",
//        "InputPassword",
//        "Confirm",
//        "List",
//        "ListMultiSelection"
//))
//
//when (componentName) {
//    "Input" -> KInquirer.promptInput("Enter Your Name:", hint = "(Full Name)")
//    "InputNumber" -> KInquirer.promptInputNumber("Enter Your Age:")
//    "InputPassword" -> KInquirer.promptInputPassword("Enter Your Password:")
//    "Confirm" -> KInquirer.promptConfirm("Is this correct?")
//    "List" -> KInquirer.promptList("Select Something?", listOf("Hello", "World", "Foo", "Bar"))
//    "ListMultiSelection" -> KInquirer.promptListMulti("Pick Some Stuff:", listOf("A", "B", "C", "D"))
//}
//

