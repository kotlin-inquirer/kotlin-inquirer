<h1 align="center">
    <a href="https://kotlin-inquirer.github.io/kotlin-inquirer/">
        <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/kotlin-inquirer-logo.png" width="40%"/>
    </a>
</h1>

[![example workflow](https://github.com/kotlin-inquirer/kotlin-inquirer/actions/workflows/gradle.yml/badge.svg)](https://github.com/kotlin-inquirer/kotlin-inquirer/actions/workflows/gradle.yml)
[![Apache License V.2](https://img.shields.io/badge/license-Apache%20V.2-blue.svg)](https://github.com/kotlin-inquirer/kotlin-inquirer/blob/master/LICENSE) 
[![](https://jitpack.io/v/kotlin-inquirer/kotlin-inquirer.svg)](https://jitpack.io/#kotlin-inquirer/kotlin-inquirer)

> A collection of common interactive command line user interfaces written in [![Pure Kotlin](https://img.shields.io/badge/100%25-kotlin-blue.svg)](https://kotlinlang.org/) inspired by [Inquirer.js](https://github.com/SBoudrias/Inquirer.js "Inquirer.js") 

<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/pizza.gif?raw=true" width="80%"/>
</p>

## :rocket: Run Demo Using [kscript](https://github.com/holgerbrandl/kscript "kscript")
Remote scriplet [raw-URL](https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/scripts/pizza.kts 
"pizza.kts") 


```
kscript https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/scripts/pizza.kts
```
Or using URL Shortener

```
kscript https://bit.ly/kotlin-inquirer-pizza
```

Or clone it

```
git clone https://github.com/kotlin-inquirer/kotlin-inquirer.git
cd kotlin-inquirer
kscript ./scripts/pizza.kts
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
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/confirm.gif?raw=true" width="80%"/>
</p>

------

#### Input
```kotlin
val comments: String = KInquirer.promptInput(message = "Any comments on your purchase experience?")
println("Comments: $comments")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input.gif?raw=true" width="80%"/>
</p>

------

#### Input Numbers
```kotlin
val quantity: BigDecimal = KInquirer.promptInputNumber(message = "How many do you need?")
println("Quantity: $quantity")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input_numbers.gif?raw=true" width="80%"/>
</p>

------

#### Input Password
```kotlin
val password: String = KInquirer.promptInputPassword(message = "Enter Your Password:", hint = "password")
println("Password: $password")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/input_password.gif?raw=true" width="80%"/>
</p>


------

#### List
```kotlin
val size: String = KInquirer.promptList(message = "What size do you need?", choices = listOf("Large", "Medium", "Small"))
println("Size: $size")
```
<p align="center">
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/list.gif?raw=true" width="80%"/>
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
  <img src="https://raw.githubusercontent.com/kotlin-inquirer/kotlin-inquirer/master/assets/checkbox.gif?raw=true" width="80%"/>
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

#### Operation
- [x] Examples
- [x] Logo
- [ ] GIFs
- [ ] Maven Central
- [ ] codecov 
