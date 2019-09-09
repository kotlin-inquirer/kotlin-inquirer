@file:MavenRepository("jitpack", "https://jitpack.io")
@file:DependsOn("com.github.kotlin-inquirer:kotlin-inquirer:v0.0.2-alpha")

import com.yg.kotlin.inquirer.components.promptListMultiObject
import com.yg.kotlin.inquirer.components.promptListObject
import com.yg.kotlin.inquirer.core.Choice
import com.yg.kotlin.inquirer.core.KInquirer


data class Food(val id: Int, val name: String, val price: Int)

val foodList = listOf(
    Food(1, "pizza", 21),
    Food(2, "pasta", 24),
    Food(3, "hamburger", 30),
    Food(4, "salad", 19),
    Food(5, "sushi", 35)
)

val selectedDishList = KInquirer.promptListMultiObject("What component do you want to test?", foodList.map { Choice(it.name, it) }, showPerPage = 3)
val selectedDish = KInquirer.promptListObject("What component do you want to test?", selectedDishList.map { Choice(it.name, it) })

println(selectedDish)
