@file:MavenRepository("jitpack", "https://jitpack.io")
@file:DependsOn("com.github.kotlin-inquirer:kotlin-inquirer:v0.0.2-alpha")

import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.components.promptInputNumber
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.components.promptListMulti
import com.yg.kotlin.inquirer.core.KInquirer
import java.math.BigDecimal

data class PizzaOrder(
    val isDelivery: Boolean,
    val phoneNumber: String,
    val size: String,
    val quantity: BigDecimal,
    val toppings: List<String>,
    val comments: String
)

println("Hi, welcome to Kotlin's Pizza")
val isDelivery: Boolean = KInquirer.promptConfirm("Is this for delivery?", default = false)
val phoneNumber: String = KInquirer.promptInput("What's your phone number?", filter = { s -> s.matches("\\d+".toRegex()) })
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
val quantity: BigDecimal = KInquirer.promptInputNumber("How many do you need?")
val toppings: List<String> = KInquirer.promptListMulti("What about the toppings?", listOf("Pepperoni", "Mushrooms", "Tomatoes", "Onions"))
val comments: String = KInquirer.promptInput("Any comments on your purchase experience?", hint = "Nope, all good!")

val order = PizzaOrder(
    isDelivery = isDelivery,
    phoneNumber = phoneNumber,
    size = size,
    quantity = quantity,
    toppings = toppings,
    comments = comments
)

println("Your order:")
println(order)
