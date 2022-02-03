import com.github.kinquirer.components.*
import com.github.kinquirer.KInquirer
import java.math.BigDecimal

fun main() {
    // Confirm
    val isDelivery: Boolean = KInquirer.promptConfirm(message = "Is this for delivery?", default = false)
    println("Is Delivery: $isDelivery")

    // Input
    val comments: String = KInquirer.promptInput(message = "Any comments on your purchase experience?")
    println("Comments: $comments")

    // Input Numbers
    val quantity: BigDecimal = KInquirer.promptInputNumber(message = "How many do you need?")
    println("Quantity: $quantity")

    // Input Password
    val password: String = KInquirer.promptInputPassword(message = "Enter Your Password:", hint = "password")
    println("Password: $password")

    // List
    val size: String =
        KInquirer.promptList(message = "What size do you need?", choices = listOf("Large", "Medium", "Small"))
    println("Size: $size")

    // Checkbox
    val toppings: List<String> = KInquirer.promptCheckbox(
        message = "What about the toppings?",
        choices = listOf(
            "Pepperoni and cheese",
            "All dressed",
            "Hawaiian",
        ),
    )
    println("Toppings: $toppings")
}
