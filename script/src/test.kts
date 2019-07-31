@file:DependsOn("com.yg.kotlin.inquirer:core:1.0")

import com.yg.kotlin.inquirer.core.components.ConfirmationComponent
import com.yg.kotlin.inquirer.core.interact

val component = ConfirmationComponent("Are You OK ?")
interact(component)
println(component.value())