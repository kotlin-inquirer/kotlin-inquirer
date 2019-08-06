#!/bin/bash
@file:DependsOn("com.yg.kotlin.inquirer:core:0.01")
@file:DependsOn("com.yg.kotlin.inquirer:components:0.01")

import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptInput
import com.yg.kotlin.inquirer.components.promptInputNumber
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.components.promptListMulti
import com.yg.kotlin.inquirer.core.KInquirer

println("Hi, welcome to Kotlin Pizza")
val isDelivery = KInquirer.promptConfirm("Is this for delivery?", default = false)
val phoneNumber = KInquirer.promptInputNumber("What's your phone number?")
val size = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
val quantity = KInquirer.promptInputNumber("How many do you need?")
val toppings = KInquirer.promptListMulti("What about the toppings?", listOf("Pepperoni", "Mushrooms", "Tomatoes"))
val comments = KInquirer.promptInput("Any comments on your purchase experience?", hint = "Nope, all good!")