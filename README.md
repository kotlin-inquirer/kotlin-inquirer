# Kotlin-Inquirer [![Build Status](https://travis-ci.org/YanivGrama/kotlin-inquirer.svg?branch=master)](https://travis-ci.org/YanivGrama/kotlin-inquirer)

 A common interactive command line user interfaces written in `Kotlin` inspired by [Inquirer.js](https://github.com/SBoudrias/Inquirer.js "Inquirer.js")


## Installation

```shell script
git clone https://github.com/YanivGrama/kotlin-inquirer.git
cd kotlin-inquirer
./gradlew clean install
```

## Usages

#### Confirm

```kotlin
val isDelivery: Boolean = KInquirer.promptConfirm("Is this for delivery?", default = false)
println("Is Delivery: $isDelivery")
```
<p align="center"><img src="/assets/confirm-component.gif?raw=true"/></p>

------

#### Input
```kotlin
val comments: String = KInquirer.promptInput("Any comments on your purchase experience?")
println("Comments: $comments")
```
<p align="center"><img src="/assets/input-component.gif?raw=true"/></p>

------

#### Input Numbers
```kotlin
val quantity: BigDecimal = KInquirer.promptInputNumber("How many do you need?")
println("Quantity: $quantity")
```
<p align="center"><img src="/assets/input-number-component.gif?raw=true"/></p>

------

#### Input Password
```kotlin
val password: String = KInquirer.promptInputPassword("Enter Your Password:", hint = "password")
println("Password: $password")
```
<p align="center"><img src="/assets/input-password-component.gif?raw=true"/></p>

------

#### List
```kotlin
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
println("Size: $size")
```
<p align="center"><img src="/assets/list-component.gif?raw=true"/></p>

------

#### List Multi Selection
```kotlin
val size: String = KInquirer.promptList("What size do you need?", listOf("Large", "Medium", "Small"))
println("Size: $size")
```
<p align="center"><img src="/assets/list-multi-component.gif?raw=true"/></p>

------

#### Todo
##### Components
- [x] Confirm
- [x] Input
- [x] Input Numbers
- [x] Input Password
- [x] List
- [x] List with multi selection
- [ ] Input validation error message
- [ ] Support Hint

##### Operation
- [ ] Maven Central
- [x] GIFs
- [x] Travis-CI
- [ ] codecov 
- [ ] Icon
- [ ] Examples


