@file:DependsOn("com.yg.kotlin.inquirer:kotlin-inquirer:0.01")

import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.components.promptInputNumber
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.components.promptListMulti
import com.yg.kotlin.inquirer.core.KInquirer
import java.math.BigDecimal

println("Hi, welcome to Kotlin Pizza")
val isDelivery: Boolean = KInquirer.promptConfirm("Is this for delivery?", default = false)
val phoneNumber: String = KInquirer.promptInput("What's your phone number?", filter = { s -> s.matches("\\d+".toRegex()) })
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
val quantity: BigDecimal = KInquirer.promptInputNumber("How many do you need?")
val toppings: List<String> = KInquirer.promptListMulti("What about the toppings?", listOf("Pepperoni", "Mushrooms", "Tomatoes", "Onions"))
val comments: String = KInquirer.promptInput("Any comments on your purchase experience?", hint = "Nope, all good!")