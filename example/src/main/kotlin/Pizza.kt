import com.github.kinquirer.components.*
import com.github.kinquirer.core.Choice
import com.github.kinquirer.KInquirer
import java.math.BigDecimal

data class PizzaOrder(
    val isDelivery: Boolean,
    val phoneNumber: String,
    val size: String,
    val quantity: BigDecimal,
    val toppings: List<String>,
    val beverage: String,
    val comments: String,
)

fun main() {
    println("Hi, welcome to Kotlin's Pizza")
    val isDelivery: Boolean = KInquirer.promptConfirm("Is this for delivery?", default = false)
    val phoneNumber: String = KInquirer.promptInput(
        message = "What's your phone number?",
        filter = { s -> s.matches("\\d+".toRegex()) },
        validation = { s -> s.length == 10 },
    )
    val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
    val quantity: BigDecimal = KInquirer.promptInputNumber("How many do you need?")
    val toppings: List<String> = KInquirer.promptCheckboxObject(
        message = "What about the toppings?",
        choices = listOf(
            Choice("Pepperoni and cheese", "pepperonicheese"),
            Choice("All dressed", "alldressed"),
            Choice("Hawaiian", "hawaiian"),
        ),
    )
    val beverage: String = KInquirer.promptList("You also get a free 2L beverage", listOf("Pepsi", "7up", "Coke"))
    val comments: String = KInquirer.promptInput(
        message = "Any comments on your purchase experience?",
        hint = "Nope, all good!",
        default = "Nope, all good!",
    )

    val order = PizzaOrder(
        isDelivery = isDelivery,
        phoneNumber = phoneNumber,
        size = size,
        quantity = quantity,
        toppings = toppings,
        beverage = beverage,
        comments = comments,
    )

    println("====== Order receipt ======")
    println(order)
}
