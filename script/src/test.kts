@file:DependsOn("com.yg.kotlin.inquirer:core:1.0")
@file:DependsOn("com.yg.kotlin.inquirer:components:1.0")

import com.yg.kotlin.inquirer.components.ConfirmComponent
import com.yg.kotlin.inquirer.core.KInquirer

fun KInquirer.promptConfirm(message: String): Boolean {
    return prompt(ConfirmComponent(message))
}

val result: Boolean = KInquirer.promptConfirm("Hello")

println(result)
