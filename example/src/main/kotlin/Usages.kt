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

    // Input Password Masked
    val passwordMasked: String = KInquirer.promptInputPassword(
        message = "Enter Your Password:",
        hint = "password",
        mask = "🤫"
    )
    println("Password: $passwordMasked")

    // List
    val size: String =
        KInquirer.promptList(message = "What size do you need?", choices = listOf("Large", "Medium", "Small"))
    println("Size: $size")

    // List View Options
    val continent: String = KInquirer.promptList(
        message = "Select a continent:",
        choices = listOf(
            "Asia",
            "Africa",
            "Europe",
            "North America",
            "South America",
            "Australia",
            "Antarctica",
        ),
        hint = "press Enter to pick",
        pageSize = 3,
        viewOptions = ListViewOptions(
            questionMarkPrefix = "🌍",
            cursor = " 😎 ",
            nonCursor = "    ",
        )


    )
    println("Continent: $continent")

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

    // Checkbox View Options
    val colors: List<String> = KInquirer.promptCheckbox(
        message = "Which colors do you prefer?",
        choices = listOf(
            "Red",
            "Green",
            "Blue",
            "Yellow",
            "Black",
            "White",
        ),
        hint = "pick a color using spacebar",
        maxNumOfSelection = 3,
        minNumOfSelection = 2,
        pageSize = 3,
        viewOptions = CheckboxViewOptions(
            questionMarkPrefix = "❓",
            cursor = " 👉 ",
            nonCursor = "    ",
            checked = "✅ ",
            unchecked = "○ ",
        )
    )
    println("Colors: $colors")
}
