<h1 align="center">
    <a href="https://kotlin-inquirer.github.io/kotlin-inquirer/">
        <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/kotlin-inquirer-logo.png" width="40%"/>
    </a>
</h1>

[![example workflow](https://github.com/kotlin-inquirer/kotlin-inquirer/actions/workflows/gradle.yml/badge.svg)](https://github.com/kotlin-inquirer/kotlin-inquirer/actions/workflows/gradle.yml)
[![Apache License V.2](https://img.shields.io/badge/license-Apache%20V.2-blue.svg)](https://github.com/kotlin-inquirer/kotlin-inquirer/blob/master/LICENSE) 
[![](https://jitpack.io/v/kotlin-inquirer/kotlin-inquirer.svg)](https://jitpack.io/#kotlin-inquirer/kotlin-inquirer)
[![codecov](https://codecov.io/gh/kotlin-inquirer/kotlin-inquirer/branch/master/graph/badge.svg?token=S4v5ziYYpA)](https://codecov.io/gh/kotlin-inquirer/kotlin-inquirer)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

> A collection of common interactive command line user interfaces written in [![Pure Kotlin](https://img.shields.io/badge/100%25-kotlin-blue.svg)](https://kotlinlang.org/) inspired by [Inquirer.js](https://github.com/SBoudrias/Inquirer.js "Inquirer.js") 

<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/pizza.gif?raw=true" width="90%"/>
</p>

## :rocket: Run Demo Using [kscript](https://github.com/holgerbrandl/kscript "kscript")
Remote scriplet [raw-URL](https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/scripts/pizza.kts 
"pizza.kts") 

```
kscript https://bit.ly/kotlin-inquirer-pizza
```

Or clone it

```
git clone https://github.com/kotlin-inquirer/kotlin-inquirer.git
cd kotlin-inquirer
kscript ./scripts/pizza.kts
```
Or without kscript

```
./gradlew shadowJar
java -jar example/build/libs/kotlin-pizza.jar
```



## :cloud: Download

### Gradle
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
dependencies {
  implementation 'com.github.kotlin-inquirer:kotlin-inquirer:0.1.0'
}
```

## :clipboard: Usages

#### Confirm

```kotlin
val isDelivery: Boolean = KInquirer.promptConfirm(message = "Is this for delivery?", default = false)
println("Is Delivery: $isDelivery")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/confirm.gif?raw=true" width="90%"/>
</p>

------

#### Input
```kotlin
val comments: String = KInquirer.promptInput(message = "Any comments on your purchase experience?")
println("Comments: $comments")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input.gif?raw=true" width="90%"/>
</p>

------

#### Input Numbers
```kotlin
val quantity: BigDecimal = KInquirer.promptInputNumber(message = "How many do you need?")
println("Quantity: $quantity")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input_numbers.gif?raw=true" width="90%"/>
</p>

------

#### Input Password
```kotlin
val password: String = KInquirer.promptInputPassword(message = "Enter Your Password:", hint = "password")
println("Password: $password")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input_password.gif?raw=true" width="90%"/>
</p>

------

#### Input Password custom mask
```kotlin
val passwordMasked: String = KInquirer.promptInputPassword(
    message = "Enter Your Password:",
    hint = "password",
    mask = "🤫"
)
println("Password: $passwordMasked")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input_password_masked.gif?raw=true" width="90%"/>
</p>

------

#### List
```kotlin
val size: String = KInquirer.promptList(message = "What size do you need?", choices = listOf("Large", "Medium", "Small"))
println("Size: $size")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/list.gif?raw=true" width="90%"/>
</p>

------

#### List with more options
```kotlin
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
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/list_view_options.gif?raw=true" width="90%"/>
</p>

------

#### Checkbox
```kotlin
val toppings: List<String> = KInquirer.promptCheckbox(
    message = "What about the toppings?",
    choices = listOf(
        "Pepperoni and cheese",
        "All dressed",
        "Hawaiian",
    ),
)
println("Toppings: $toppings")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/checkbox.gif?raw=true" width="90%"/>
</p>

------

#### Checkbox with more options
```kotlin
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
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/checkbox_view_options.gif?raw=true" width="90%"/>
</p>

------

### :crystal_ball: Roadmap
#### Components
- [x] Confirm
- [x] Input
- [x] Input Numbers
- [x] Input Password
- [x] List
- [x] Checkbox
- [x] Input validation error message
- [x] Support Hint
- [x] Better package name
- [x] Add examples for `ViewOption`
- [ ] Support List/Checkbox Fuzzy search
- [ ] Support List/Checkbox autocomplete
- [ ] Add DSL support
- [ ] Consider non static function for prompts


#### Operation
- [x] Examples
- [x] Logo
- [x] GIFs
- [x] codecov
- [ ] Maven Central
 
